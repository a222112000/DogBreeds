package com.tourism.breeddogs.data

import com.tourism.breeddogs.domain.BreedRepository
import javax.inject.Inject

class BreedRepositoryImpl @Inject constructor(private val api: BreedApi) : BreedRepository {
    override suspend fun getAllBreeds(): BreedsList{
        return api.getAllBreeds()
    }
}