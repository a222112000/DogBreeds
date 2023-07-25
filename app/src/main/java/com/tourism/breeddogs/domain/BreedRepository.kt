package com.tourism.breeddogs.domain

import com.tourism.breeddogs.data.BreedsList
import com.tourism.breeddogs.data.DogBreed
import com.tourism.breeddogs.data.Message

interface BreedRepository{
    suspend fun getAllBreeds():BreedsList
}