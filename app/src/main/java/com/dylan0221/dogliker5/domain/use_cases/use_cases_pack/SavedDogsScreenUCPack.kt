package com.dylan0221.dogliker5.domain.use_cases.use_cases_pack

import com.dylan0221.dogliker5.domain.use_cases.DeleteDogItemFBUseCase
import com.dylan0221.dogliker5.domain.use_cases.GetAllDogItemFBUseCase
import javax.inject.Inject

data class SavedDogsScreenUCPack @Inject constructor(
    val getAllDogItemFBUseCase: GetAllDogItemFBUseCase,
    val deleteDogItemFBUseCase: DeleteDogItemFBUseCase
)