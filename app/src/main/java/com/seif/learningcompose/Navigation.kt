package com.seif.learningcompose

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) { // tell navHost how our screen will look like
            MainScreen(navController = navController)
        }
        composable(
            route = Screen.DetailsScreen.route + "/{name}",
            arguments = listOf(
                navArgument("name"){
                    type = NavType.StringType
                    defaultValue = "default"
                    // nullable = true
                }
            )
        ) { entry: NavBackStackEntry ->
            entry.arguments?.getString("name")?.let { it ->
                DetailsScreen(name = it)
            }
        }
    }
}

@Composable
fun MainScreen(navController: NavController) {
    var textStateValue by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 40.dp),
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = textStateValue,
            onValueChange = {
                textStateValue = it
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = {
            // navigate to details screen
                         navController.navigate(Screen.DetailsScreen.route + "/$textStateValue")
        }, modifier = Modifier.align(CenterHorizontally)) {
            Text(
                text = "To Details Screen"
            )
        }
    }
}

@Composable
fun DetailsScreen(name: String) {

    Box(contentAlignment = Center, modifier = Modifier.fillMaxSize()) {
        Text(text = "Welcome $name")
    }
}

// "/{name}": mandatory argument
// "?name={name}": optional argument if we didn't pass something it will take the default value
