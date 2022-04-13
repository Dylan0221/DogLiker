package com.dylan0221.dogliker5.data.remote.retrofit

import com.dylan0221.dogliker5.domain.model.DogItem

data class DogItemDto(
    val message: String,
    val status: String
){
    fun toDogItem(): DogItem =
        DogItem(
            image = message,
            status = status,
        )
}
