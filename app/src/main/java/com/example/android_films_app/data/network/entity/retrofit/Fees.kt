package com.example.android_films_app.data.network.entity.retrofit


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Fees(
    @SerialName("russia")
    val russia: Russia? = null,
    @SerialName("usa")
    val usa: Usa? = null,
    @SerialName("world")
    val world: World? = null
)