package com.example.android_films_app.data.network.entity.retrofit


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExternalId(
    @SerialName("imdb")
    val imdb: String? = null,
    @SerialName("kpHD")
    val kpHD: String? = null,
    @SerialName("tmdb")
    val tmdb: Int? = null
)