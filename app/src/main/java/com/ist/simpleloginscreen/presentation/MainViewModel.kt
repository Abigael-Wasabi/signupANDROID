package com.ist.simpleloginscreen.presentation

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.toObject
import com.google.firebase.storage.FirebaseStorage
import com.ist.simpleloginscreen.app.SERVICES
import com.ist.simpleloginscreen.app.USERS
import com.ist.simpleloginscreen.data.Event
import com.ist.simpleloginscreen.data.Product
import com.ist.simpleloginscreen.data.ServicesData
import com.ist.simpleloginscreen.data.UserData
import com.ist.simpleloginscreen.presentation.screens.proj.Item
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.UUID
import javax.inject.Inject


/**
 * ViewModel class for the main screen of the application.
 *
 * This class is responsible for handling the business logic and data operations
 * related to the main screen of the application.
 *
 * @property auth The instance of FirebaseAuth used for authentication.
 * @property db The instance of FirebaseFirestore used for database operations.
 * @property storage The instance of FirebaseStorage used for storage operations.
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    val auth: FirebaseAuth, val db: FirebaseFirestore, val storage: FirebaseStorage,
) : ViewModel() {

    /**
     * ViewModel class for the main screen.
     *
     * This class holds the state of the main screen, including whether the user is signed in,
     * whether there is an ongoing operation in progress, the user data, and any popup notifications.
     */
    val signedIn = mutableStateOf(false)
    val inProgress = mutableStateOf(false)
    val userData = mutableStateOf<UserData?>(null)
    val popupNotification = mutableStateOf<Event<String>?>(null)
    val cartItems: MutableState<List<CartItem>> = mutableStateOf(emptyList())

    /**
     * Initializes the MainViewModel.
     * - Checks if the user is signed in by accessing the current user from the authentication service.
     * - Updates the value of the `signedIn` LiveData based on whether the current user is null or not.
     * - If the current user is not null, retrieves the user data using the user's unique identifier (UID).
     */

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

    /**
    method  responsible for handling the signup process,
    takes the username, email, and password as parameters.*/

    //user exists //return an error message//create user//pass model data to firestore

    fun onSignup(userName: String, email: String, pass: String, navController: NavController) {
        inProgress.value = true
        if (userName.isEmpty() || email.isEmpty() || pass.isEmpty()) {
            popupNotification.value = Event("Please enter all details")
            return
        }
        db.collection(USERS).whereEqualTo("email", email).get()
            .addOnSuccessListener {
                if (it.documents.size > 0) {
                    popupNotification.value = Event("Email already exists")
                    return@addOnSuccessListener
                }
            }
        if (pass.length < 6) {
            popupNotification.value = Event("Password must be at least 6 characters")
            return
        } else {
            auth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        signedIn.value = true
                        createOrUpdateProfile(username = userName)
                        navController.navigate("services")
                        popupNotification.value = Event("You have successfully registered user")
                    } else {
                        popupNotification.value = Event("Email already exists")
                    }
                }
        }

    }


    fun onLogin(email: String, pass: String, navController: NavController) {
        inProgress.value = true
        auth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    signedIn.value = true
                    getUserData(auth.currentUser?.uid ?: "")
                    navController.navigate("services")
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

    /**Creates or updates the user profile with the provided information.*/
    //!if null value(s) the existing value(s) will b used

    private fun createOrUpdateProfile(
        name: String? = null,
        username: String? = null,
        bio: String? = null,
        imageUrl: String? = null,
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

    /**Retrieves user data from the Firestore database based on the provided user ID.*/
    //uid The ID of the user whose data needs to be retrieved.

    fun getUserData(uid: String) {
        inProgress.value = true
        db.collection(USERS).document(uid).get().addOnSuccessListener {
            val user = it.toObject<UserData>()
            userData.value = user
            inProgress.value = false
            popupNotification.value = Event("User data retrieved successfully")
        }
            .addOnFailureListener { exc ->
                handleException(exc, "cannot get user data")
                inProgress.value = false
            }

    }

    /**Handles exceptions and displays a notification message.*/
    //exception The exception to handle. Defaults to null.
    //customMessage A custom message to display along with the exception. Defaults to an empty string.

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
        auth.signOut()
        signedIn.value = false
        userData.value = null
        popupNotification.value = Event("Logged out")
    }

    /**Uploads an image to the Firebase storage.*/

    //uri The URI of the image to be uploaded.
    //onSuccess Callback function to be executed when the image upload is successful.


    private fun uploadImage(uri: Uri, onSuccess: (Uri) -> Unit) {
        inProgress.value = true


        val storageRef = storage.reference
        val uuid = UUID.randomUUID()
        val imageRef = storageRef.child("$uuid")
        val uploadTask = imageRef.putFile(uri)

        uploadTask.addOnSuccessListener {
            val result = it.metadata?.reference?.downloadUrl
            Log.d("uploadImage: $result", "uploadImage: $result")
            result?.addOnSuccessListener(onSuccess)
        }.addOnFailureListener { exc ->
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

    //create service
    private fun onCreateService(imageUri: Uri, description: String, onServiceSuccess: () -> Unit) {
        //fetch userid
        val uid = auth.currentUser?.uid
        //get the current username
        val username = userData.value?.username


        //check if the current user id is null
        if (uid !== null) {
            //create a unique id for the post
            val serviceUuid = UUID.randomUUID().toString()
            //Assign the services data model a variable
            val service = ServicesData(
                serviceId = serviceUuid,
                username = username,
                serviceImage = imageUri.toString(),
                serviceDescription = description
            )
            db.collection(
                SERVICES
            ).document(serviceUuid).set(service).addOnSuccessListener {
                popupNotification.value = Event("Service successfully created")
                inProgress.value = false
                onServiceSuccess.invoke()
            }.addOnFailureListener { exc ->
                handleException(exc, "Unable to create service")
                inProgress.value = false
            }

        } else {
            handleException(customMessage = "Error: username unavailable. Unable to create service")
            onLogout()
            inProgress.value = false
        }
    }

    fun onNewService(uri: Uri, description: String, onServiceSuccess: () -> Unit) {
        uploadImage(uri) {
            onCreateService(it, description, onServiceSuccess)
        }
    }

    private fun updateServiceImageData(imageUrl: String) {


    }

    private fun convertServices(
        documents: QuerySnapshot,
        outState: MutableState<List<ServicesData>>,
    ) {
        val newServices = mutableListOf<ServicesData>()
        documents.forEach { doc ->
            val services = doc.toObject<ServicesData>()
            newServices.add(services)
        }
        val sortedServices = newServices.sortedByDescending { it.time }
        outState.value = sortedServices
    }

    data class CartItem(
        val itemId: String,
        val itemName: String,
        val itemPrice: Double,
        val itemImage: Int,
//        var quantity: Int,
    )


    // add an item to the cart
    fun addToCart(item: Product) {
        val existingItem = cartItems.value.find { it.itemId == item.id }
        if (existingItem != null) {
            // If item already exists in the cart, increment quantity
//            existingItem.quantity++
            cartItems.value = cartItems.value // Trigger recomposition
        } else {
            // If item does not exist in the cart, add it with quantity 1
            val cartItem = CartItem(
                itemId = item.id, // Assuming your Product class has an id property
                itemName = item.name,
                itemPrice = item.price,
                itemImage = item.imageResId,
//                quantity = 1, //initial quantity is 1
            )
            cartItems.value = cartItems.value + listOf(cartItem)
        }
    }


    // remove an item from the cart
    fun removeFromCart(itemName: String) {
        cartItems.value = cartItems.value.filter { it.itemName != itemName }
    }

//     Method to update the quantity of an item in the cart
//    fun updateCartItemQuantity(itemId: String, newQuantity: Int) {
//        cartItems.value = cartItems.value.map {
//            if (it.itemId == itemId) {
//                it.copy(quantity = newQuantity)
//            } else {
//                it
//            }
//        }
//    }

//     Method to calculate the total price of items in the cart
//    fun calculateTotalPrice(): Double {
//        return cartItems.value.sumOf { it.itemPrice * it.quantity }
//    }

    fun getSelectedItems(): List<Item> {
        return cartItems.value.map { cartItem ->
            Item(
                name = cartItem.itemName,
                price = cartItem.itemPrice,
                imageUrl = cartItem.itemImage
            )
        }
    }

    // Other methods related to cart operations...
}