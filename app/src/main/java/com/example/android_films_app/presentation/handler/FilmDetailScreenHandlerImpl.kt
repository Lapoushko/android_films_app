package com.example.android_films_app.presentation.handler

import androidx.navigation.NavHostController
import com.example.android_films_app.presentation.extension.canGoBack

/**
 * @author Lapoushko
 * Функции экрана деталей фильма
 */
interface FilmDetailScreenHandler{
    fun onToBack()
}

/**
 * Реализация функций деталей фильмов
 * @param navController контроллер навигации
 */
class FilmDetailScreenHandlerImpl(val navController: NavHostController) : FilmDetailScreenHandler {
    override fun onToBack() {
        if (navController.canGoBack) {
            navController.popBackStack()
        }
    }

}