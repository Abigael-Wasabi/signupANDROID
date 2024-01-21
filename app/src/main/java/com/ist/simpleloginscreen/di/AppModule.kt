package com.ist.simpleloginscreen.di

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.storage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
class AppModule {

    /**
     * Provides an instance of [FirebaseAuth] for authentication.
     */
    @Provides
    fun provideAuthentication(): FirebaseAuth = Firebase.auth

    /**
     * Provides an instance of [FirebaseFirestore] for Firestore database operations.
     */
    @Provides
    fun provideFirestore(): FirebaseFirestore = Firebase.firestore

    /**
     * Provides an instance of [FirebaseStorage] for Firebase storage operations.
     */
    @Provides
    fun provideStorage(): FirebaseStorage = Firebase.storage


}