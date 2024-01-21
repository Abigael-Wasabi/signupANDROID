package com.ist.simpleloginscreen.presentation

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import com.google.firebase.storage.FirebaseStorage
import com.ist.simpleloginscreen.app.USERS
import com.ist.simpleloginscreen.data.Event
import com.ist.simpleloginscreen.data.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val auth: FirebaseAuth, val db: FirebaseFirestore, val storage: FirebaseStorage
) : ViewModel() {

    val signedIn = mutableStateOf(false)
    val inProgress = mutableStateOf(false)
    val userData = mutableStateOf<UserData?>(null)
    val popupNotification = mutableStateOf<Event<String>?>(null)

    init {
        //sign out user
        auth.signOut()
        //Use the currentUser property to get the currently signed-in user.
        val currentUser = auth.currentUser
        signedIn.value = currentUser != null
        currentUser?.let {
            getUserData(it.uid)
        }

    }

    // user exists
    //return an error message
    //create user
    //pass model data to firestore
    fun onSignup(username: String, email: String, pass: String) {
        Log.d("Signup", "Username: $username, Email: $email, Password: $pass")
        //validate all fields are filled
        if (username.isEmpty() || email.isEmpty() || pass.isEmpty()) {
            Log.d("Signup", "Please fill in all the fields")
            popupNotification.value = Event("Please fill in all the fields")
            return
        }
        inProgress.value = true
        //check if username already exists if not create user
        db.collection(USERS).whereEqualTo("username", username).get()
            .addOnSuccessListener { documents ->
                if (documents.size() > 0) {
                    handleException(customMessage = "Username already exists")
                    inProgress.value = false
                } else {/*
                    *  function completes, either successfully or with an
                    * error, it triggers the addOnCompleteListener.
                    * This listener receives a Task object,
                    * which represents the result of the asynchronous operation.
                    * The Task object is passed to the lambda expression as the task parameter.
                     */
                    auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            signedIn.value = true
                            createOrUpdateProfile(username = username)
                        } else {
                            handleException(task.exception, "Signup failed")
                        }
                        inProgress.value = false
                    }

                }
            }

            .addOnFailureListener { }
    }


    fun onLogin(email: String, pass: String) {

        inProgress.value = true
        auth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    signedIn.value = true
                    getUserData(auth.currentUser?.uid ?: "")
                    //test whether the user is signed in
                    //handleException(customMessage = "Login successful")
                } else {
                    handleException(task.exception, "Login failed")
                    inProgress.value = false
                }
            }
            .addOnFailureListener { exc ->
                handleException(exc, "Login failed")
                inProgress.value = false
            }
    }

    private fun createOrUpdateProfile(
        name: String? = null,
        username: String? = null,
        bio: String? = null,
        imageUrl: String? = null
    ) {
        val uid = auth.currentUser?.uid
        val userData = UserData(
            userId = uid,
            name = name ?: userData.value?.name,
            username = username ?: userData.value?.username,
            bio = bio ?: userData.value?.bio,
            imageUrl = imageUrl ?: userData.value?.imageUrl,
            role = userData.value?.role,
            services = userData.value?.services
        )

        uid?.let { uid ->
            inProgress.value = true
            db.collection(USERS).document(uid).get().addOnSuccessListener {
                if (it.exists()) {
                    it.reference.update(userData.toMap()).addOnSuccessListener {
                        this.userData.value = userData
                        inProgress.value = false
                    }.addOnFailureListener {
                        handleException(it, "Profile update failed")
                        inProgress.value = false
                    }

                } else {
                    db.collection(USERS).document(uid).set(userData)
                    getUserData(uid)
                    inProgress.value = false
                }

            }.addOnFailureListener { exc ->
                handleException(exc, "cannot create user")
                inProgress.value = false
            }
        }

    }

    fun getUserData(uid: String) {
        inProgress.value = true
        db.collection(USERS).document(uid).get().addOnSuccessListener {

            val user = it.toObject<UserData>()
            userData.value = user
            inProgress.value = false
            //popupNotification.value = Event("User data retrieved successfully")
        }

            .addOnFailureListener { exc ->
                handleException(exc, "cannot get user data")
                inProgress.value = false
            }

    }

    fun handleException(exception: Exception? = null, customMessage: String = "") {
        exception?.printStackTrace()
        val errorMsg = exception?.localizedMessage ?: ""
        val message = if (customMessage.isEmpty()) errorMsg else "$customMessage: $errorMsg"
        popupNotification.value = Event(message)
    }

    fun updateProfileData(name: String, username: String, bio: String) {
        createOrUpdateProfile(name, username, bio)
    }

    fun onLogout() {
        TODO("Not yet implemented")
    }

    private fun uploadImage(uri: Uri, onSuccess: (Uri) -> Unit) {
        inProgress.value = true

        val storageRef = storage.reference
        val uuid = UUID.randomUUID()
        val imageRef = storageRef.child("images/$uuid")
        val uploadTask = imageRef.putFile(uri)

        uploadTask
            .addOnSuccessListener {
                val result = it.metadata?.reference?.downloadUrl
                result?.addOnSuccessListener(onSuccess)
            }
            .addOnFailureListener { exc ->
                handleException(exc)
                inProgress.value = false
            }
    }

    fun uploadProfileImage(uri: Uri) {
        uploadImage(uri) {
            createOrUpdateProfile(imageUrl = it.toString())
            updateServiceImageData(it.toString())
        }
    }

    //Upload service image
    private fun updateServiceImageData(toString: String) {
        //get current user data from firestore
        //use the .whereEqualto method to get the userId
        //post service image to firestore
    }

    //create service
    private fun onCreateService() {
        //pass on data from the Services datasource
    }

    //Add roles controller


}