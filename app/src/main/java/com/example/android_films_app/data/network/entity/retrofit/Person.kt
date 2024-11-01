package com.example.android_films_app.data.network.entity.retrofit


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Person(
    @SerialName("description")
    val description: String? = null,
    @SerialName("enName")
    val enName: String? = null,
    @SerialName("enProfession")
    val enProfession: String? = null,
    @SerialName("id")
    val id: Int? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("photo")
    val photo: String? = null,
    @SerialName("profession")
    val profession: String? = null
)