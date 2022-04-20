package com.dylan0221.dogliker.presentation.states

import com.dylan0221.dogliker.domain.model.DogItem

data class DogItemState(
    val success: DogItem? = null,
    val error: String = "",
    val loading: Boolean = false
)