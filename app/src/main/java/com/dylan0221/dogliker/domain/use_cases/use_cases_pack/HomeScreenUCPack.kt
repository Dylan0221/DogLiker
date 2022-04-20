package com.dylan0221.dogliker.domain.use_cases.use_cases_pack

import com.dylan0221.dogliker.domain.use_cases.AddDogItemFBUseCase
import com.dylan0221.dogliker.domain.use_cases.GetDogDtoUseCase
import javax.inject.Inject


data class HomeScreenUCPack @Inject constructor (
    val addDogItemFBUseCase: AddDogItemFBUseCase,
    val getDogDtoUseCase: GetDogDtoUseCase
)