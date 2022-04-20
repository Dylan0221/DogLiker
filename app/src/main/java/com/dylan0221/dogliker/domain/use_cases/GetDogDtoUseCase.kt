package com.dylan0221.dogliker.domain.use_cases

import com.dylan0221.dogliker.data.remote.retrofit.DogItemDto
import com.dylan0221.dogliker.domain.repository.DogLikerRepository
import com.dylan0221.dogliker.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetDogDtoUseCase @Inject constructor(
    private val repository: DogLikerRepository
) {

    /*
    Uses A Generic Resource Class to emit different values depending on the State of
    the DogItemDto.
     */
    operator fun invoke(): Flow<Resource<DogItemDto>> = flow {
        try {
            emit(Resource.Loading())

            val dogItemDto = repository.getDogDto()

            emit(Resource.Success(dogItemDto))



        }catch (e: HttpException){

            emit(Resource.Error(e.localizedMessage ?: "An unexpected error has occured"))

        }catch (e: IOException){

            emit(Resource.Error("Couldn't reach the server, please check your internet connection"))

        }
    }
}