package com.tourism.breeddogs.domain

import android.util.Log
import com.tourism.breeddogs.common.instance.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DogBreedImageUseCase @Inject constructor(private val images: BreedsUseCase) {

    suspend fun getBreedDogImages(breed: String): Flow<Resource<List<String>>>{
        Log.d("YUY","Images ${images.getDogBreedImages(breed)}")
        return images.getDogBreedImages(breed)
    }
}