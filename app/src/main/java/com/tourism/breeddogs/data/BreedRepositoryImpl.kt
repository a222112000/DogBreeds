package com.tourism.breeddogs.data

import com.tourism.breeddogs.domain.BreedRepository
import retrofit2.Response
import javax.inject.Inject

class BreedRepositoryImpl @Inject constructor(private val api: BreedApi) : BreedRepository {
    override suspend fun fetchDogBreeds(): Response<BreedsList> {
        return api.fetchDogBreeds()
    }

    override suspend fun fetchDogBreedImages(breedName: String): Response<DogBreedImagesResponse> {
        return api.fetchDogBreedImages(breedName)
    }

}