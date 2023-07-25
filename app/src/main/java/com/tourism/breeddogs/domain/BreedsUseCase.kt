package com.tourism.breeddogs.domain

import com.tourism.breeddogs.common.instance.Resource
import com.tourism.breeddogs.ui.BreedsState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class BreedsUseCase @Inject constructor(private val repository: BreedRepository) {

     operator fun invoke(): Flow<Resource<BreedsState>> = flow {
        try {
            emit(Resource.Loading())
            val breeds = repository.getAllBreeds()
            emit(Resource.Success(BreedsState(breeds = breeds)))
        }catch (e: Exception){
            emit(Resource.Error("Some thing went wrong \n ${e.message}"))
        }
    }
}