package com.dylan0221.dogliker5.presentation.states

import com.dylan0221.dogliker5.domain.model.DogItem

data class DogItemState(
    val success: DogItem? = null,
    val error: String = "",
    val loading: Boolean = false
)