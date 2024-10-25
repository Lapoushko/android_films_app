package com.example.android_films_app.data.storage.entity

import android.net.Uri

/**
 * @author Lapoushko
 *
 * Фильм
 * @param id айди фильма
 * @param country страна создания
 * @param directors режисёры
 * @param budget бюджет фильма
 * @param name имя фильма
 * @param genres жанры фильмов
 * @param description описание фильма
 * @param imageUri путь до изображения фильма
 */
class FilmDb(
    val id: Long?,
    val name: String?,
    val country: String?,
    val directors: List<String>?,
    val budget: Long?,
    val genres: List<String>?,
    val description: String?,
    val imageUri: Uri?
)