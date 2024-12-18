package com.example.android_films_app.presentation.handler

import androidx.navigation.NavHostController
import com.example.android_films_app.presentation.extension.canGoBack
import com.example.android_films_app.presentation.model.FilmItem
import com.example.android_films_app.presentation.screen.model.ScreenFilm

/**
 * @author Lapoushko
 * Функции экрана всех фильмов
 */
interface FilmScreenHandler {
    fun onToFilmDetail(filmItem: FilmItem)
}

/**
 * Реализация функций всех фильмов
 * @param navController контроллер навигации
 */
class FilmsScreenHandlerImpl(
    val navController: NavHostController,
) : FilmScreenHandler {
    override fun onToFilmDetail(filmItem: FilmItem) {
        navController.navigate(
            ScreenFilm.FilmDetails(
                film = filmItem
            )
        )
        if (navController.canGoBack) {
            navController.popBackStack()
        }
    }
}