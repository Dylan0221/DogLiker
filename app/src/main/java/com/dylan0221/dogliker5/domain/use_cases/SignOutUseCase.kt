package com.dylan0221.dogliker5.domain.use_cases

import com.dylan0221.dogliker5.domain.repository.DogLikerRepository
import javax.inject.Inject

class SignOutUseCase @Inject constructor(
    private val repository: DogLikerRepository
) {

    operator fun invoke() =
        repository.signOut()

}