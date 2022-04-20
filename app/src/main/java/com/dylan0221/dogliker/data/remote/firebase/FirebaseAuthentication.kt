package com.dylan0221.dogliker.data.remote.firebase

import android.app.Activity
import androidx.core.app.ActivityCompat.startActivityForResult
import com.dylan0221.dogliker.utils.constants.RequestCodes.Companion.GOOGLE_RC_SIGN_IN
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class FirebaseAuthentication(
    private val auth: FirebaseAuth
) {

    //Method for sign in using Google. Still needs to be implemented in the app
    fun googleAuth(googleSignInClient: GoogleSignInClient, activity: Activity){
        val signInIntent = googleSignInClient.signInIntent

        startActivityForResult(activity,signInIntent, GOOGLE_RC_SIGN_IN, null)

    }


    //Method for sign in with Facebook. Still needs to be implemented in the app
    fun faceBookAuth(){

    }


    fun emailLogin(email: String, password: String,) =
        auth.signInWithEmailAndPassword(email, password)



    fun emailRegistration(email: String, password: String) =
        auth.createUserWithEmailAndPassword(email, password)




    fun signOut() =
        Firebase.auth.signOut()






}
