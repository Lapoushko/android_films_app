package com.example.android_films_app.data.network.entity.retrofit


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Backdrop(
    @SerialName("previewUrl")
    val previewUrl: String? = null,
    @SerialName("url")
    val url: String? = null
)