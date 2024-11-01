package com.example.android_films_app.data.network.entity

import android.net.Uri
import com.example.android_films_app.util.URISerializer
import kotlinx.serialization.Serializable

/**
 * @author Lapoushko
 *
 * Фильм api
 * @param country страна создания
 * @param directors режисёры
 * @param budget бюджет фильма
 * @param name имя фильма
 * @param genres жанры фильмов
 * @param description описание фильма
 * @param imageUri путь до изображения фильма
 */
@Serializable
class FilmResponse(
    val id: Long?,
    val name: String?,
    val country: List<String>? ,
    val directors: List<String>?,
    val budget: Long?,
    val genres: List<String>?,
    val description: String?,
    @Serializable(with = URISerializer::class)
    val imageUri: Uri?
)