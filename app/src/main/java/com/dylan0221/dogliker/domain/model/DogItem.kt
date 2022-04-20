package com.dylan0221.dogliker.domain.model

data class DogItem(
    var id: Int? = null,
    val image: String,
    val status: String,
    var isLiked: Boolean = false
)
