package com.tourism.breeddogs.ui

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tourism.breeddogs.common.instance.Resource
import com.tourism.breeddogs.data.DogBreed
import com.tourism.breeddogs.domain.BreedsUseCase
import com.tourism.breeddogs.domain.DogBreedImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreedsViewModel @Inject constructor(private val breedsUseCase: BreedsUseCase,
                                          private val getImages: DogBreedImageUseCase,
                                          savedStateHandle: SavedStateHandle,
): ViewModel() {

    private var _breed = mutableStateOf(BreedsState())
    val breeds: State<BreedsState> = _breed

    private var _breedImages = mutableStateOf(BreedImagesState())
    val breedImages = _breedImages

    init {
        getBreedsList()
        savedStateHandle.get<String>("imageUrl")?.let {
            getDogBreedImages(it)
        }
    }

     fun getBreedsList(){
        breedsUseCase().onEach {
            when(it){
                is Resource.Success ->{
                    _breed.value = _breed.value.copy(
                        isLoading = false,
                        breeds = it.data?.map {
                            DogBreed(name = it.name, imageUrl = it.imageUrl)
                        }
                    )
                }
                is Resource.Error ->{
                    _breed.value = BreedsState(error = it.message.toString())
                }
                is Resource.Loading ->{
                    _breed.value = BreedsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getDogBreedImages(name: String){
        viewModelScope.launch {
            getImages.getBreedDogImages(name).collect{result->
                _breedImages.value = when(result){
                    is Resource.Success ->{
                        _breedImages.value.copy(isLoading = false,breeds = result.data)
                    }
                    is Resource.Error->{
                        _breedImages.value.copy(error = result.message.toString())
                    }
                    is Resource.Loading ->{
                        _breedImages.value.copy(isLoading = true)
                    }
                }
            }
        }
    }
}