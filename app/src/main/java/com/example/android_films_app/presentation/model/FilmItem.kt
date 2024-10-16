package com.example.android_films_app.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

/**
 * @author Lapoushko
 *
 * Фильм
 * @param name имя фильма
 * @param genres жанры фильмов
 * @param description описание фильма
 * @param imageUri путь до изображения фильма
 */
@Serializable
@Parcelize
class FilmItem(
    val name: String,
    val country: String? = "Страна не указана",
    val directors: List<String>,
    val budget: Long = 0L,
    val genres: List<String>,
    val description: String? = "Пострадав в результате несчастного случая, богатый аристократ Филипп нанимает в помощники человека, который менее всего подходит для этой работы, – молодого жителя предместья Дрисса, только что освободившегося из тюрьмы. Несмотря на то, что Филипп прикован к инвалидному креслу, Дриссу удается привнести в размеренную жизнь аристократа дух приключений.",
    val imageUri: String? = "https://avatars.mds.yandex.net/get-kinopoisk-image/1773646/d2f33a70-7c85-4884-8d17-01e86e763732/1920x"
) : Parcelable