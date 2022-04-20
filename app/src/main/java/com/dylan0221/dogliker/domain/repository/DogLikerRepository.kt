package com.dylan0221.dogliker.domain.repository

import android.app.Activity
import com.dylan0221.dogliker.domain.model.DogItem
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.firestore.DocumentSnapshot
import com.dylan0221.dogliker.data.remote.retrofit.DogItemDto as DogItemDto

interface DogLikerRepository {
    suspend fun getDogDto(): DogItemDto

    fun createDogList(userId: String)

    fun addDogFB(userId: String, dog: DogItem): Task<Void>

    fun deleteDogItemFb(dog: DogItem, userId: String): Task<Void>

    fun getAllDogItemFB(userId: String): Task<DocumentSnapshot>

    fun loginEmail(email: String, password: String): Task<AuthResult>

    fun googleAuth(googleSignInClient: GoogleSignInClient, activity: Activity)

    suspend fun facebookAuth()

    fun signUpEmail(email: String, password: String): Task<AuthResult>

    fun signOut()

}