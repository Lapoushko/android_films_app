package com.example.android_films_app.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.android_films_app.presentation.navigation.graph.NavGraph
import com.example.android_films_app.presentation.navigation.ui.BottomBar
import com.example.android_films_app.presentation.screen.model.ScreenBar

/**
 * @author Lapoushko
 *
 * Нижний бар навигации
 * @param navController контроллер навигации
 */
@Composable
fun BottomBarScreen(
    navController: NavHostController
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val screens = listOf(
        ScreenBar.Films.route,
        ScreenBar.Favourite.route,
        ScreenBar.Home.route,
        ScreenBar.Notifications.route
    )

    val showBottomBar = currentDestination?.route in screens

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = {
            if (showBottomBar) {
                BottomBar(
                    navController = navController,
                )
            }
        }

    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            NavGraph(navController = navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomBarScreenPreview() {
    BottomBarScreen(navController = rememberNavController())
}