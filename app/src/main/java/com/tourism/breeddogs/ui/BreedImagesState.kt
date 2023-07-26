package com.tourism.breeddogs.ui

data class BreedImagesState(
    val isLoading: Boolean = false,
    var breeds: List<String>? = null,
    val error: String = ""
)