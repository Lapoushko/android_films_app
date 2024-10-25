package com.example.android_films_app.data.network.entity.retrofit


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Rating(
    @SerialName("await")
    val await: Double? = null,
    @SerialName("filmCritics")
    val filmCritics: Double? = null,
    @SerialName("imdb")
    val imdb: Double? = null,
    @SerialName("kp")
    val kp: Double? = null,
    @SerialName("russianFilmCritics")
    val russianFilmCritics: Double? = null,
    @SerialName("tmdb")
    val tmdb: Double? = null
)