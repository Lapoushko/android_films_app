package com.example.android_films_app.presentation.screen.model

import com.example.android_films_app.presentation.model.FilmItem
import kotlinx.serialization.Serializable

/**
 * @author Lapoushko
 *
 * Экран фильма
 */
sealed class ScreenFilm {
    @Serializable
    data class FilmDetails(val film: FilmItem) : ScreenFilm()
}