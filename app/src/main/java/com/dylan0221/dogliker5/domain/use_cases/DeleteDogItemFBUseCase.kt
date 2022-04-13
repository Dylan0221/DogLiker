package com.dylan0221.dogliker5.domain.use_cases

import com.dylan0221.dogliker5.domain.model.DogItem
import com.dylan0221.dogliker5.domain.repository.DogLikerRepository
import javax.inject.Inject

class DeleteDogItemFBUseCase @Inject constructor(
    private val repository: DogLikerRepository
) {

    operator fun invoke(userId: String, dog: DogItem) {
        repository.deleteDogItemFb(userId = userId, dog = dog)
    }

}