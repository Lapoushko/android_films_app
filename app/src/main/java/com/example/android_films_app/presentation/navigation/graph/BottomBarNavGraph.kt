package com.example.android_films_app.presentation.navigation.graph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
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
 */
@Composable
fun BottomBarNavGraph(
    navController: NavHostController,
    bottomBarState: MutableState<Boolean>
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
                film = film.film
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