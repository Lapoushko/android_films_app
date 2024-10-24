package com.example.android_films_app.presentation.util

import android.os.Build
import android.os.Bundle
import androidx.navigation.NavType
import com.example.android_films_app.presentation.model.FilmItem
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * @author Lapoushko
 * Кастомный NavType для навигации сложного объекта FilmItem
 */
val CustomNavType = object : NavType<FilmItem>(isNullableAllowed = false){
    override fun get(bundle: Bundle, key: String): FilmItem {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            bundle.getParcelable(key, FilmItem::class.java) as FilmItem
        } else{
            bundle.getParcelable<FilmItem>(key) as FilmItem
        }
    }

    override fun parseValue(value: String): FilmItem {
        return Json.decodeFromString<FilmItem>(value)
    }

    override fun put(bundle: Bundle, key: String, value: FilmItem) {
        bundle.putParcelable(key, value)
    }

    override fun serializeAsValue(value: FilmItem): String = Json.encodeToString<FilmItem>(value)

    override val name: String = FilmItem::class.java.name
}