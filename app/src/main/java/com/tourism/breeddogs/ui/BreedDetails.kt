package com.tourism.breeddogs.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BreedDetails(imageUrl: String?,navigation: NavController) {
    Scaffold(
        topBar = {
            TopBar(navigation)
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (imageUrl != null) {
                AsyncImage(
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colorScheme.onPrimaryContainer)
                        .size(208.dp).border(
                            width = 1.dp,
                            color = Color.Red,
                            shape = RoundedCornerShape(size = 1.dp)
                        )
                        .clip(RoundedCornerShape(topEnd = 1.dp, topStart = 1.dp)),
                    contentScale = ContentScale.FillWidth,
                    model =
                    imageUrl,
                )
            } else {
                Text("Error: No image URL provided")
            }
        }
    }
}