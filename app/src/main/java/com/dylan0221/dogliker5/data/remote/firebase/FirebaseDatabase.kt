package com.dylan0221.dogliker5.data.remote.firebase

import android.content.ContentValues.TAG
import android.util.Log
import com.dylan0221.dogliker5.domain.model.DogItem
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseDatabase(
    private val db: FirebaseFirestore
) {

    //Method that creates an empty list on Firebase using the user's unique ID
    //Only Used when a User has signed up for the first time
    fun createDogList(userId: String){
        val map = hashMapOf(
            "LikedDogs" to emptyList<Any>()
        )
        db.collection("users")
            .document(userId)
            .set(map)
            .addOnSuccessListener { Log.d(TAG, "LikedDogs list succesfully created") }
            .addOnFailureListener{e -> Log.w(TAG, "Error writing document", e)}
    }

    //Adds dog picture to the List
    fun addDog(userId: String, dog: DogItem): Task<Void> =
        db.collection("users")
            .document(userId)
            .update("LikedDogs", FieldValue.arrayUnion(dog.image))
            .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }


    // Deletes dog picture from the list. Still need to be implemented in the app
    fun deleteDog(userId: String, dog: DogItem): Task<Void> =
        db.collection("users")
            .document(userId)
            .update( "LikedDogs", FieldValue.arrayRemove(dog.image) )


    // Gives a list of all images saved inside the firestore list.
    fun getAllDogs(userId: String): Task<DocumentSnapshot> =
        db.collection("users")
            .document(userId)
            .get()
}