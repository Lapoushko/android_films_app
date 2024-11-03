package com.example.android_films_app.data.storage.entity

/**
 * @author Lapoushko
 *
 * Фильм
 * @param id айди фильма
 * @param countries страна создания
 * @param directors режисёры
 * @param budget бюджет фильма
 * @param name имя фильма
 * @param genres жанры фильмов
 * @param description описание фильма
 * @param imageUri путь до изображения фильма
 */
data class FilmDb(
    val id: Long?,
    val name: String?,
    val countries: List<String>?,
    val directors: List<String>?,
    val budget: Long?,
    val genres: List<String>?,
    val description: String?,
    val year: Int?,
    val imageUri: String?,
    val isFavourite: Boolean? = false
)