package com.example.android_films_app.data.network.entity.retrofit


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Votes(
    @SerialName("await")
    val await: Int? = null,
    @SerialName("filmCritics")
    val filmCritics: Int? = null,
    @SerialName("imdb")
    val imdb: Int? = null,
    @SerialName("kp")
    val kp: String? = null,
    @SerialName("russianFilmCritics")
    val russianFilmCritics: Int? = null,
    @SerialName("tmdb")
    val tmdb: Int? = null
)