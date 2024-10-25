package com.example.android_films_app.presentation.model

import android.net.Uri
import android.os.Parcelable
import com.example.android_films_app.util.URISerializer
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

/**
 * @author Lapoushko
 *
 * Фильм
 * @param country страна создания
 * @param directors режисёры
 * @param budget бюджет фильма
 * @param name имя фильма
 * @param genres жанры фильмов
 * @param description описание фильма
 * @param imageUri путь до изображения фильма
 */
@Serializable
@Parcelize
data class FilmItem(
    val name: String,
    val country: String,
    val directors: List<String>,
    val budget: Long,
    val genres: List<String>,
    val description: String,
    @Serializable(with = URISerializer::class)
    val imageUri: Uri
) : Parcelable