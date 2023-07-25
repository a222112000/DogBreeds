package com.tourism.breeddogs.ui

sealed class Screen(val route: String) {
    object MainScreen: Screen("breeds_screen")
    object DogDetails: Screen("dog_details")
    object DogItem: Screen("dog_item")
}