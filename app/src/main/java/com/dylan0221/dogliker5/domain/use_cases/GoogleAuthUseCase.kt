package com.dylan0221.dogliker5.domain.use_cases

import android.app.Activity
import com.dylan0221.dogliker5.domain.repository.DogLikerRepository
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import javax.inject.Inject

class GoogleAuthUseCase @Inject constructor(
    private val repository: DogLikerRepository
) {

    operator fun invoke(googleSignInClient: GoogleSignInClient, activity: Activity){
        repository.googleAuth(googleSignInClient,activity)
    }
}