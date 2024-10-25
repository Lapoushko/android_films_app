package com.example.android_films_app.data.network.entity.retrofit


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReviewInfo(
    @SerialName("count")
    val count: Int? = null,
    @SerialName("percentage")
    val percentage: String? = null,
    @SerialName("positiveCount")
    val positiveCount: Int? = null
)