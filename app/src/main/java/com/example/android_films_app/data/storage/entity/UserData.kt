package com.example.android_films_app.data.storage.entity

import kotlinx.serialization.Serializable

/**
 * @author Lapoushko
 */
@Serializable
data class UserData(
    val name: String? = null,
    val description: String? = null,
    val photo: String? = null,
    val resume: String? = null
)