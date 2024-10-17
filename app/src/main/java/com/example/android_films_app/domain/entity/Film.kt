package com.example.android_films_app.domain.entity

/**
 * @author Lapoushko
 */
class Film(
    val name: String,
    val country: String? = "Страна не указана",
    val directors: List<String>,
    val budget: Long = 0L,
    val genres: List<String>,
    val description: String = "",
    val imageUri: String = "https://avatars.mds.yandex.net/get-kinopoisk-image/1773646/d2f33a70-7c85-4884-8d17-01e86e763732/1920x"
)