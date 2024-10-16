package com.example.android_films_app.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.android_films_app.presentation.navigation.graph.BottomBarNavGraph
import com.example.android_films_app.presentation.navigation.ui.BottomBar

/**
 * @author Lapoushko
 *
 * Нижний бар навигации
 * @param navController контроллер навигации
 */
@Composable
fun BottomBarScreen(navController: NavHostController) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = { BottomBar(navController = navController) }

    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ){
            BottomBarNavGraph(navController = navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomBarScreenPreview() {
    BottomBarScreen(navController = rememberNavController())
}