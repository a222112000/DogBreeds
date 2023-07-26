package com.tourism.breeddogs.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BreedsScreen(
    navigation: NavController,
    viewModel: BreedsViewModel = hiltViewModel()
) {
    val state = viewModel.breeds.value
    Scaffold(
        topBar = {
            TopBar(navigation)
        }
    ) {
        Surface(
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.fillMaxSize()
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 70.dp), Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    state.breeds?.let {
                        LazyColumn(
                            content = {

                                items(it) {

                                    BreedsItem(
                                        breed = it,
                                        imageUrl = it.imageUrl,
                                        navController = navigation
                                    )
                                }

                            }, contentPadding = PaddingValues(16.dp)
                        )
                        if (state.error.isNotEmpty()) {
                            Text(
                                text = viewModel.breeds.value.error,
                                color = MaterialTheme.colorScheme.error,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 20.dp)

                            )
                        }
                        if (state.isLoading) {
                            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                        }

                    }
                }
            }
        }
    }
}

