package com.example.android_films_app.data.network.entity.retrofit

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ListFilmRetrofit(
    @SerialName("docs")
    val docs: List<FilmRetrofit>? = listOf()
)
