package com.dylan0221.dogliker.data.remote.retrofit

import com.dylan0221.dogliker.domain.model.DogItem

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
