package com.tourism.breeddogs.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.tourism.breeddogs.R

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BreedDetails(imageUrl: String?,
                 navigation: NavController,
                 viewModel: BreedsViewModel = hiltViewModel(),
                 count: Int? = null
) {

    val state = viewModel.breedImages.value
    Scaffold(
        topBar = {
            TopBar(navigation, imageUrl, count = state.breeds?.size)
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (imageUrl != null) {
                state.breeds?.let {
                    LazyColumn(modifier = Modifier.padding(top = 74.dp)) {
                        items(it) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(White, shape = shape),
                                contentAlignment = Alignment.Center
                            ) {
                                AsyncImage(
                                    contentDescription = state.breeds!!.indexOf(it).toString(),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(color = MaterialTheme.colorScheme.onPrimaryContainer)
                                        .size(208.dp)
                                        .padding(6.dp)
                                        .border(
                                            width = 1.dp,
                                            color = Color.Red,
                                            shape = RoundedCornerShape(size = 1.dp)
                                        )
                                        .clip(RoundedCornerShape(topEnd = 1.dp, topStart = 1.dp)),
                                    contentScale = ContentScale.FillWidth,
                                    model =
                                    ImageRequest.Builder(LocalContext.current)
                                        .data(it)
                                        .memoryCacheKey(it)
                                        .diskCacheKey(it)
                                        .placeholder(R.drawable.baseline_logo_dev_24)
                                        .error(R.drawable.baseline_logo_dev_24)
                                        .fallback(R.drawable.baseline_logo_dev_24)
                                        .diskCachePolicy(CachePolicy.ENABLED)
                                        .memoryCachePolicy(CachePolicy.ENABLED)
                                        .transformations(CircleCropTransformation())
                                        .build(),
                                    placeholder = painterResource(R.drawable.baseline_logo_dev_24),
                                )
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(
                                        modifier = Modifier
                                            .padding(4.dp)
                                            .align(Alignment.CenterHorizontally),
                                        text = state.breeds!!.indexOf(it).plus(1).toString(),
                                        fontWeight = FontWeight.Bold,
                                        color = Green
                                    )
                                }
                            }
                        }
                    }
                }
            } else {
                Text("Error: No image URL provided")
            }
        }
    }
}