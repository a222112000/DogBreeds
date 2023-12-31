package com.tourism.breeddogs.ui

import android.graphics.drawable.Icon
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.tourism.breeddogs.R
import com.tourism.breeddogs.data.DogBreed

@Composable
fun BreedsItem(
    breed: DogBreed,
    navController: NavController,
    imageUrl: String? = null,
    breedsViewModel: BreedsViewModel = hiltViewModel()
){
    val images = breedsViewModel.breedImages.value
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Card(modifier = Modifier.fillMaxWidth()) {
            breed.let {

                Column(modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        navController.navigate(Screen.DogDetails.route + "/" + it.name)
                    }) {
                    Row(modifier = Modifier
                        .fillMaxSize().padding(9.dp),
                        horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(text = it.name, modifier = Modifier
                            .padding(9.dp)
                            .clickable {
                            }, fontSize = 18.sp,
                            fontWeight = FontWeight.Bold)
                        Icon(painter = painterResource(id = R.drawable.baseline_arrow_forward_ios_24),
                            contentDescription = null, modifier = Modifier.padding(9.dp))
                    }
                }
            }

        }

    }
}