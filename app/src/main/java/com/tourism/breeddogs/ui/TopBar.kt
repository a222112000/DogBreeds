package com.tourism.breeddogs.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.tourism.breeddogs.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navController: NavController) {
    TopAppBar(
        title = {
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Row() {

                    Card(
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .size(48.dp)
                            .testTag("circle"),
                        shape = CircleShape,
                    ) {

                        Image(
                           painter =  painterResource(R.drawable.baseline_logo_dev_24),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )

                    }
                    Text(
                        text = if(navController.currentDestination?.route == "breeds_screen"){
                                   "List Of Breeds"
                               } else {
                            "Selected Breed"
                        },
                        modifier = Modifier.padding(top = 6.dp, end = 8.dp).fillMaxWidth(),
                        style = TextStyle(color = Color.Black, fontSize = 27.sp),
                        textAlign = TextAlign.Start,
                    )
                }
            }
        },
         navigationIcon = {if (navController.previousBackStackEntry != null) {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back", tint = Color.Green
                )
            }
        }
        }
    )
}