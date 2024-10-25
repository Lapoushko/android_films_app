package com.example.android_films_app.data.network.entity.retrofit


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReleaseYear(
    @SerialName("end")
    val end: Int? = null,
    @SerialName("start")
    val start: Int? = null
)