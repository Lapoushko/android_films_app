package com.example.android_films_app.data.network.entity.retrofit


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SeasonsInfo(
    @SerialName("episodesCount")
    val episodesCount: Int? = null,
    @SerialName("number")
    val number: Int? = null
)