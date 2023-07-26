package com.tourism.breeddogs.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BreedApi {

    @GET("breeds/list/all")
    suspend fun fetchDogBreeds(): Response<BreedsList>

    @GET("breed/{breed_name}/images/random/10")
    suspend fun fetchDogBreedImages(@Path("breed_name") breedName: String): Response<DogBreedImagesResponse>
}