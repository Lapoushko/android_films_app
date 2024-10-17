package com.example.android_films_app.data.storage.entity

/**
 * @author Lapoushko
 */
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
    val id: Long,
    val name: String,
    val country: String? = "Страна не указана",
    val directors: List<String>,
    val budget: Long = 0L,
    val genres: List<String>,
    val description: String = "",
    val imageUri: String = "https://avatars.mds.yandex.net/get-kinopoisk-image/1773646/d2f33a70-7c85-4884-8d17-01e86e763732/1920x"
)