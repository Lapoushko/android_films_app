package com.example.android_films_app.presentation.screen.model

/**
 * @author Lapoushko
 */
sealed class ScreenUser(
    val route: String,
) {
    data object Screen: ScreenUser(route = ROUTE_TAG)

    companion object{
        private const val ROUTE_TAG = "ROUTE_TAG"
    }
}