package com.example.android_films_app.data.network.entity.retrofit


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Item(
    @SerialName("logo")
    val logo: Logo? = Logo(),
    @SerialName("name")
    val name: String? = ""
)