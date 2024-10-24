package com.example.android_films_app.presentation.screen.model

import com.example.android_films_app.presentation.model.FilmItem
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * @author Lapoushko
 *
 * Экран деталей фильма
 */
sealed class ScreenFilm {
    @Serializable
    data class FilmDetails(val film: FilmItem) : ScreenFilm() {
        override fun toString(): String {
            return "FilmDetails/${Json.encodeToString(film)}"
        }
    }
}

