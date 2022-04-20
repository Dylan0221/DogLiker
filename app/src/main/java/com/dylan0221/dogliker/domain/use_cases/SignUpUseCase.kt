package com.dylan0221.dogliker.domain.use_cases

import com.dylan0221.dogliker.domain.repository.DogLikerRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val repository: DogLikerRepository
) {

    operator fun invoke(email: String? = null, password: String? = null) =
        repository.signUpEmail(email!!, password!!)


}