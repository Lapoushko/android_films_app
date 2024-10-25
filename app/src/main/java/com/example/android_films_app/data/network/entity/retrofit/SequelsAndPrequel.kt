package com.example.android_films_app.data.network.entity.retrofit


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SequelsAndPrequel(
    @SerialName("alternativeName")
    val alternativeName: String? = "",
    @SerialName("enName")
    val enName: String? = "",
    @SerialName("id")
    val id: Int? = 0,
    @SerialName("name")
    val name: String? = "",
    @SerialName("poster")
    val poster: Poster? = Poster(),
    @SerialName("rating")
    val rating: Rating? = Rating(),
    @SerialName("type")
    val type: String? = "",
    @SerialName("year")
    val year: Int? = 0
)