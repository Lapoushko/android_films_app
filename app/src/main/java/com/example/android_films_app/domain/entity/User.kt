package com.example.android_films_app.domain.entity

/**
 * @author Lapoushko
 * @param name имя
 * @param description описание
 * @param photo фото профиля
 * @param resume резюме
 */
class User(
    val name: String,
    val description: String,
    val photo: String,
    val resume: String
)