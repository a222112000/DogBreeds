package com.tourism.breeddogs.domain

import android.util.Log
import com.tourism.breeddogs.common.di.asyncAll
import com.tourism.breeddogs.common.instance.Resource
import com.tourism.breeddogs.data.DogBreed
import com.tourism.breeddogs.data.DogBreedWithSubBreeds
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class BreedsUseCase @Inject constructor(private val repository: BreedRepository) {

     operator fun invoke(): Flow<Resource<List<DogBreed>>> = flow {
         val dogBreeds = mutableListOf<DogBreed>()
         val resource = repository.fetchDogBreeds()
         val dogBreedNameWithSubBreedList = mutableListOf<DogBreedWithSubBreeds>()

         try {
             emit(Resource.Loading())
             resource.body()?.message?.entries?.forEach {
                 dogBreedNameWithSubBreedList.add(
                     DogBreedWithSubBreeds(
                         it.key,
                         it.value.joinToString(",")
                     )
                 )
             }
             withContext(Dispatchers.IO) {
                 prepareDogsBreedListWithImage(
                     this,
                     dogBreedNameWithSubBreedList, dogBreedList = dogBreeds
                 )
             }
             emit(Resource.Success(data = dogBreeds))
         }catch (e: Exception){
                 emit(Resource.Error("Some thing went wrong \n ${e.message}"))

         }
    }

     suspend fun getDogBreedImages(breedName: String): Flow<Resource<List<String>>> = flow{
        try {
            val res = repository.fetchDogBreedImages(breedName)
            when (res.isSuccessful) {
                true -> {
                emit(Resource.Loading())
                    res.body()?.let { body ->
                             emit(Resource.Success(data = body.message))
                    } ?: emit(Resource.Error(message = res.code().toString()))
                }
                false -> emit(Resource.Error(message = res.code().toString()))
            }
        } catch (e: Exception) {
            Log.e("NETWORK_API_ERROR", "Cannot get dog breed images ${e.hashCode()}")
             emit(Resource.Error(message = e.hashCode().toString()))
        }
    }

    private suspend fun prepareDogsBreedListWithImage(
        scope: CoroutineScope,
        dogBreedNameWithSubBreedList: List<DogBreedWithSubBreeds>,
        dogBreedList: MutableList<DogBreed>,
    ) {
        var iterator = 0
        scope.asyncAll(dogBreedNameWithSubBreedList) { repository.fetchDogBreedImages(it.name) }
            .awaitAll() //Awaits for completion of given deferred values without blocking a thread and
            .forEach { breedImageResponse ->
                breedImageResponse.body().let { breedImage ->
                    dogBreedList.add(
                        DogBreed(
                            dogBreedNameWithSubBreedList[iterator].name,
                            dogBreedNameWithSubBreedList[iterator].subBreeds
                        )
                    )
                }
                iterator++
            }
    }
}