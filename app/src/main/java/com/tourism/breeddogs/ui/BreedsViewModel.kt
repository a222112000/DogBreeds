package com.tourism.breeddogs.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tourism.breeddogs.common.instance.Resource
import com.tourism.breeddogs.domain.BreedsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class BreedsViewModel @Inject constructor(private val breedsUseCase: BreedsUseCase
): ViewModel() {

    private val _breed = mutableStateOf(BreedsState())
    val breeds: State<BreedsState> = _breed

    init {
        getBreedsList()
    }

     fun getBreedsList(){
        breedsUseCase().onEach {
            when(it){
                is Resource.Success ->{
                    _breed.value = BreedsState(breeds = it.data?.breeds)
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
}