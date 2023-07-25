package com.tourism.breeddogs

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tourism.breeddogs.ui.BreedDetails
import com.tourism.breeddogs.ui.BreedsItem
import com.tourism.breeddogs.ui.BreedsScreen
import com.tourism.breeddogs.ui.Screen
import com.tourism.breeddogs.ui.theme.BreedDogsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BreedDogsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Screen.MainScreen.route){
                        composable(route = "${Screen.DogDetails.route}/{imageUrl}",
                            arguments = listOf(navArgument("imageUrl"){
                                type = NavType.StringType
                            }
                            )
                        ){backStack ->
                            BreedDetails(imageUrl = backStack.arguments?.getString("imageUrl"), navigation = navController)
                        }

                        composable(route = Screen.MainScreen.route){
                            BreedsScreen(navigation = navController)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BreedDogsTheme {
        Greeting("Android")
    }
}