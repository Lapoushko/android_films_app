package com.example.android_films_app.data.storage.data

import com.example.android_films_app.data.storage.entity.FilmDb

/**
 * @author Lapoushko
 */
class ExampleLocalData {
    val films: List<FilmDb> = generateSequence(1) { it + 1 }
        .map { index ->
            FilmDb(
                id = index.toLong(),
                name = "Фильм - $index",
                genres = listOf("Хоррор", "Комедия"),
                imageUri = "https://avatars.mds.yandex.net/get-kinopoisk-image/1900788/bd3c56b3-3681-4137-9335-b849c42ed6ea/1920x",
                directors = listOf("Оливье Накаш", "Эрик Толедано"),
            )
        }
        .take(10)
        .toList()
}