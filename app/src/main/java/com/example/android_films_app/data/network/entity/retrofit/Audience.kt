package com.example.android_films_app.data.network.entity.retrofit


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Audience(
    @SerialName("count")
    val count: Int? = null,
    @SerialName("country")
    val country: String? = null
)