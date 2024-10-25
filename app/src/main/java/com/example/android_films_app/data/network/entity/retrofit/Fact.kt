package com.example.android_films_app.data.network.entity.retrofit


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Fact(
    @SerialName("spoiler")
    val spoiler: Boolean? = null,
    @SerialName("type")
    val type: String? = null,
    @SerialName("value")
    val value: String? = null
)