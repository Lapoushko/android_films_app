package com.example.android_films_app.presentation.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.android_films_app.presentation.handler.FilmsScreenHandler
import com.example.android_films_app.presentation.model.FilmItem
import com.example.android_films_app.presentation.screen.FilmDetailsScreen
import com.example.android_films_app.presentation.screen.FilmsScreen
import com.example.android_films_app.presentation.screen.Screen

/**
 * @author Lapoushko
 */

@Composable
fun BottomBarNavGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = Screen.Films.route
    ){
        composable(route = Screen.Films.route){
            FilmsScreen(FilmsScreenHandler(navController))
        }
        composable(route = Screen.FilmDetails.route){ navBackStackEntry ->
            val film: FilmItem = navBackStackEntry.toRoute()
            FilmDetailsScreen(film)
        }
        composable(route = Screen.Home.route){
            /* TODO */
        }
        composable(route = Screen.Favourite.route){
            /* TODO */
        }
        composable(route = Screen.Notifications.route) {
            /* TODO */
        }
    }
}