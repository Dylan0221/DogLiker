package com.dylan0221.dogliker5.domain.use_cases.use_cases_pack

import com.dylan0221.dogliker5.domain.use_cases.*
import javax.inject.Inject

data class UseCasesPack @Inject constructor(
    val addDogItemFBUseCase: AddDogItemFBUseCase,
    val createDogListFBUseCase: CreateDogListFBUseCase,
    val deleteDogItemFBUseCase: DeleteDogItemFBUseCase,
    val getAllDogItemFBUseCase: GetAllDogItemFBUseCase,
    val getDogDtoUseCase: GetDogDtoUseCase,
    val googleAuthUC: GoogleAuthUseCase,
    val loginUseCase: LoginUseCase,
    val signUpUseCase: SignUpUseCase,
    val signOutUseCase: SignOutUseCase,
)