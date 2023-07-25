package com.tourism.breeddogs.data

import retrofit2.http.GET

interface BreedApi {

    @GET("/api/breed/hound/images")
    suspend fun getAllBreeds(): BreedsList
}