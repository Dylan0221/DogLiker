package com.dylan0221.dogliker5.domain.use_cases

import com.dylan0221.dogliker5.domain.model.DogItem
import com.dylan0221.dogliker5.domain.repository.DogLikerRepository
import com.google.android.gms.tasks.Task
import javax.inject.Inject

class AddDogItemFBUseCase @Inject constructor(
    private val repository: DogLikerRepository,
) {

    operator fun invoke(userId: String, dog: DogItem): Task<Void> =
        repository.addDogFB(userId = userId, dog = dog)

}