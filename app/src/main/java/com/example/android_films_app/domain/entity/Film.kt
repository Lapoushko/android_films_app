package com.example.android_films_app.domain.entity

import android.net.Uri

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
    val imageUri: Uri
)