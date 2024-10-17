package com.example.android_films_app.data.storage.data

import com.example.android_films_app.data.storage.entity.FilmDb
import com.example.android_films_app.presentation.extension.toFormattedUri

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
                imageUri = "https://avatars.mds.yandex.net/get-kinopoisk-image/1773646/3d0978ff-54f8-4cc2-b495-8c9dbd4ba708/1920x".toFormattedUri(),
                directors = listOf("Оливье Накаш", "Эрик Толедано"),
                description = "Пострадав в результате несчастного случая, богатый аристократ Филипп нанимает в помощники человека, который менее всего подходит для этой работы, – молодого жителя предместья Дрисса, только что освободившегося из тюрьмы. Несмотря на то, что Филипп прикован к инвалидному креслу, Дриссу удается привнести в размеренную жизнь аристократа дух приключений."
            )
        }
        .take(10)
        .toList()
}