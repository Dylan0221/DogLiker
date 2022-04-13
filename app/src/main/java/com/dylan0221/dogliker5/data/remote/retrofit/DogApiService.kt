package com.dylan0221.dogliker5.data.remote.retrofit

import retrofit2.http.GET

interface DogApiService {
    //Gets random Dog image from the Dog APi
    @GET("image/random")
    suspend fun getDog(): DogItemDto

}