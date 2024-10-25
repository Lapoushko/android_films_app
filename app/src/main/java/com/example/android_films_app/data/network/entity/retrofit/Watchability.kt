package com.example.android_films_app.data.network.entity.retrofit


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Watchability(
    @SerialName("items")
    val items: List<ItemX>? = listOf()
)