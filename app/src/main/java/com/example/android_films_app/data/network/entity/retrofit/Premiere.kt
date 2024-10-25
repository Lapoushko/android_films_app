package com.example.android_films_app.data.network.entity.retrofit


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Premiere(
    @SerialName("bluray")
    val bluray: String? = null,
    @SerialName("cinema")
    val cinema: String? = null,
    @SerialName("country")
    val country: String? = null,
    @SerialName("digital")
    val digital: String? = null,
    @SerialName("dvd")
    val dvd: String? = null,
    @SerialName("russia")
    val russia: String? = null,
    @SerialName("world")
    val world: String? = null
)