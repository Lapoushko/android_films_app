package com.example.android_films_app.presentation.model

import android.media.Image

/**
 * @author Lapoushko
 *
 * Фильм
 * @param name имя фильма
 * @param category категория фильма
 * @param description описание фильма
 * @param image изображение фильма
 */
class FilmItem(
    val name: String,
    val category: String,
    val description: String? = null,
    val image: Image? = null
)