package com.example.android_films_app.domain.entity

import android.net.Uri

/**
 * @author Lapoushko
 *
 * Фильм доменного слоя
 * @param countries страна создания
 * @param directors режисёры
 * @param budget бюджет фильма
 * @param name имя фильма
 * @param genres жанры фильмов
 * @param description описание фильма
 * @param imageUri путь до изображения фильма
 */
class Film(
    val name: String,
    val countries: List<String>,
    val directors: List<String>,
    val budget: Long,
    val genres: List<String>,
    val description: String,
    val imageUri: Uri
)