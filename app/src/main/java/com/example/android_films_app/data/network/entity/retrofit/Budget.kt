package com.example.android_films_app.data.network.entity.retrofit


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Budget(
    @SerialName("currency")
    val currency: String? = null,
    @SerialName("value")
    val value: Long? = null
)