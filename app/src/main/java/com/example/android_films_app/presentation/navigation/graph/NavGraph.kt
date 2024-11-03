package com.example.android_films_app.presentation.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.android_films_app.presentation.extension.toFormattedUri
import com.example.android_films_app.presentation.handler.FavouritesScreenHandlerImpl
import com.example.android_films_app.presentation.handler.FilmDetailScreenHandlerImpl
import com.example.android_films_app.presentation.handler.FilmsScreenHandlerImpl
import com.example.android_films_app.presentation.handler.HomeScreenHandlerImpl
import com.example.android_films_app.presentation.handler.NotificationsScreenHandlerImpl
import com.example.android_films_app.presentation.model.FilmItem
import com.example.android_films_app.presentation.screen.FavouritesScreen
import com.example.android_films_app.presentation.screen.FilmDetailsScreen
import com.example.android_films_app.presentation.screen.FilmsScreen
import com.example.android_films_app.presentation.screen.HomeScreen
import com.example.android_films_app.presentation.screen.NotificationsScreen
import com.example.android_films_app.presentation.screen.model.ScreenBar
import com.example.android_films_app.presentation.screen.model.ScreenFilm
import com.example.android_films_app.presentation.util.CustomNavType
import kotlin.reflect.typeOf

/**
 * @author Lapoushko
 * Граф навигации
 * @param navController контроллер навигации
 */
@Composable
fun NavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = ScreenBar.Films.route
    ) {
        composable(route = ScreenBar.Films.route) {
            FilmsScreen(
                filmsScreenHandler = FilmsScreenHandlerImpl(
                    navController = navController,
                )
            )
        }
        composable<ScreenFilm.FilmDetails>(
            typeMap = mapOf(typeOf<FilmItem>() to CustomNavType)
        ) { backStackEntry ->
            val film = backStackEntry.toRoute<ScreenFilm.FilmDetails>()
            FilmDetailsScreen(
                filmDetailScreenHandler = FilmDetailScreenHandlerImpl(
                    navController = navController
                ),
                film = FilmItem(
                    name = film.film.name,
                    country = film.film.country,
                    directors = film.film.directors,
                    genres = film.film.genres,
                    description = film.film.description,
                    budget = film.film.budget,
                    year = film.film.year,
                    imageUri = film.film.imageUri.toString()
                        .toFormattedUri(oldChar = '\\', newChar = '/')
                )
            )
        }
        composable(route = ScreenBar.Home.route) {
            HomeScreen(
                homeScreenHandler = HomeScreenHandlerImpl(
                    navController = navController
                )
            )
        }
        composable(route = ScreenBar.Favourite.route) {
            FavouritesScreen(
                favouritesScreenHandler = FavouritesScreenHandlerImpl(
                    navController = navController
                )
            )
        }
        composable(route = ScreenBar.Notifications.route) {
            NotificationsScreen(
                notificationScreenHandler = NotificationsScreenHandlerImpl(
                    navController = navController
                )
            )
        }
    }
}