package com.dylan0221.dogliker5.domain.use_cases

import com.dylan0221.dogliker5.domain.repository.DogLikerRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import javax.inject.Inject

class GetAllDogItemFBUseCase @Inject constructor(
    private val repository: DogLikerRepository
) {

    operator fun invoke(userId: String): Task<DocumentSnapshot> =
        repository.getAllDogItemFB(userId)

}