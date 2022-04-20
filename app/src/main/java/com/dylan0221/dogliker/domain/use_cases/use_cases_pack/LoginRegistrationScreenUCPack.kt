package com.dylan0221.dogliker.domain.use_cases.use_cases_pack

import com.dylan0221.dogliker.domain.use_cases.CreateDogListFBUseCase
import com.dylan0221.dogliker.domain.use_cases.LoginUseCase
import com.dylan0221.dogliker.domain.use_cases.SignOutUseCase
import com.dylan0221.dogliker.domain.use_cases.SignUpUseCase
import javax.inject.Inject

data class LoginRegistrationScreenUCPack @Inject constructor(
    val signUpUseCase: SignUpUseCase,
    val signOutUseCase: SignOutUseCase,
    val loginUseCase: LoginUseCase,
    val createDogListFBUseCase: CreateDogListFBUseCase
)