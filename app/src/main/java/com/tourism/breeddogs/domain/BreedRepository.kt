package com.tourism.breeddogs.domain

import com.tourism.breeddogs.data.BreedsList
import com.tourism.breeddogs.data.DogBreedImagesResponse
import retrofit2.Response

interface BreedRepository{
    suspend fun fetchDogBreeds(): Response<BreedsList>
    suspend fun fetchDogBreedImages(breedName: String): Response<DogBreedImagesResponse>
}