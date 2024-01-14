package com.ist.simpleloginscreen.repo

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.ist.simpleloginscreen.di.remote.FirebaseService
import com.ist.simpleloginscreen.model.User
import kotlinx.coroutines.tasks.await

class UserRepository(private val firebaseService: FirebaseService) {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    suspend fun registerUser(email: User, password: String): User? {
        try {
            // new user,email n passw
            val result = auth.createUserWithEmailAndPassword(email.toString(), password).await()

            // Convert FirebaseUser to your User model
            val user = result.user?.let { mapFirebaseUserToUser(it) }

            return user
        } catch (e: Exception) {
            println("error")
            return null
        }
    }

    suspend fun loginUser(email: String, password: String): User? {
        try {
            // Sign in with email and passw
            val result = auth.signInWithEmailAndPassword(email, password).await()

            // Convert FirebaseUser to your User model
            val user = result.user?.let { mapFirebaseUserToUser(it) }

            return user
        } catch (e: Exception) {
            println("error")
            return null
        }
    }

    // Map FirebaseUser to my User model
    private fun mapFirebaseUserToUser(firebaseUser: FirebaseUser): User {
        return User(
            firebaseUser.uid, firebaseUser.email ?: "",
            username = null,
            phone = null,
            password = null,
            roles = null,
        )
    }
}


