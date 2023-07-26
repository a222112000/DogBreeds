package com.tourism.breeddogs.ui

import com.tourism.breeddogs.data.DogBreed

data class BreedsState(
    val isLoading: Boolean = false,
    var breeds: List<DogBreed>? = null,
    val error: String = ""
)
