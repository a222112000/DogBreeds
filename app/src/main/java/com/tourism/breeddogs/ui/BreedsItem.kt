package com.tourism.breeddogs.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.tourism.breeddogs.data.BreedsList
import com.tourism.breeddogs.data.DogBreed
import com.tourism.breeddogs.data.Message
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun BreedsItem(
    breed: String,
    navController: NavController,
    imageUrl: String? = null
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Card(modifier = Modifier.fillMaxWidth()) {
                breed.let {
                        AsyncImage(
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    if (imageUrl != null) {
                                        if(imageUrl.isNotEmpty()) {
                                            val encodedUrl = URLEncoder.encode(imageUrl, StandardCharsets.UTF_8.toString())
                                            navController.navigate("${Screen.DogDetails.route}/${encodedUrl}")
                                        }
                                    }
                                }
                                .background(color = MaterialTheme.colorScheme.onPrimaryContainer)
                                .size(208.dp)
                                .border(
                                    width = 1.dp,
                                    color = Color.Red,
                                    shape = RoundedCornerShape(size = 1.dp)
                                )
                                .clip(RoundedCornerShape(topEnd = 1.dp, topStart = 1.dp)),
                            contentScale = ContentScale.FillWidth,
                            model = it,
                            )
                    Log.d("YUY","dd ${it}")
                    }
                }


    }
}