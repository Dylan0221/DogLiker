package com.dylan0221.dogliker5.data.repository

import android.app.Activity
import com.dylan0221.dogliker5.data.remote.firebase.FirebaseAuthentication
import com.dylan0221.dogliker5.data.remote.firebase.FirebaseDatabase
import com.dylan0221.dogliker5.data.remote.retrofit.DogApiService
import com.dylan0221.dogliker5.data.remote.retrofit.DogItemDto
import com.dylan0221.dogliker5.domain.model.DogItem
import com.dylan0221.dogliker5.domain.repository.DogLikerRepository
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import javax.inject.Inject

class DogLikerRepositoryImpl @Inject constructor(
    private val api: DogApiService,
    private val fbAuth: FirebaseAuthentication,
    private val fbData: FirebaseDatabase
): DogLikerRepository {


    override suspend fun getDogDto(): DogItemDto =
        api.getDog()

    override fun createDogList(userId: String) {
        fbData.createDogList(userId)
    }


    override fun addDogFB(userId: String, dog: DogItem): Task<Void> =
        fbData.addDog(userId,dog)



    override fun deleteDogItemFb(dog: DogItem, userId: String): Task<Void> =
        fbData.deleteDog(dog = dog, userId = userId)


    override fun getAllDogItemFB(userId: String): Task<DocumentSnapshot> =
        fbData.getAllDogs(userId)


    override fun loginEmail(email: String, password: String) =
        fbAuth.emailLogin(email, password)



    override fun googleAuth(googleSignInClient: GoogleSignInClient, activity: Activity) {
        fbAuth.googleAuth(googleSignInClient,activity)
        TODO("Not Yet Implemented")
    }


    override suspend fun facebookAuth() {
        TODO("Not Yet Implemented")

    }

    override fun signUpEmail(email: String, password: String) =
        fbAuth.emailRegistration(email,password)



    override fun signOut() =
        fbAuth.signOut()

}
