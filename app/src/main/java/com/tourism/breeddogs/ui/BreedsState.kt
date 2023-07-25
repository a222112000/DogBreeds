package com.tourism.breeddogs.ui

import com.tourism.breeddogs.data.BreedsList
import com.tourism.breeddogs.data.DogBreed
import com.tourism.breeddogs.data.Message

data class BreedsState(
    val isLoading: Boolean = false,
    var breeds: BreedsList? = null,
    val error: String = ""
)
