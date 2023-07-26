package com.tourism.breeddogs.domain

import com.tourism.breeddogs.common.instance.Resource
import com.tourism.breeddogs.data.DogBreed
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DataUseCase @Inject constructor(private val breedsUseCase: BreedsUseCase) {

     operator fun invoke(): Flow<Resource<List<DogBreed>>>{
        return breedsUseCase.invoke()
    }
}