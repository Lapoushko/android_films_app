package com.example.android_films_app.presentation.mapper

import androidx.core.text.isDigitsOnly
import com.example.android_films_app.domain.entity.Film
import com.example.android_films_app.presentation.extension.toFormattedString
import com.example.android_films_app.presentation.model.FilmItem
import javax.inject.Inject

/**
 * @author Lapoushko
 * фильм ui в фильм domain
 */
interface FilmItemToFilmMapper {
    operator fun invoke(filmItem: FilmItem): Film
}

class FilmItemToFilmMapperImpl @Inject constructor() : FilmItemToFilmMapper {
    override fun invoke(filmItem: FilmItem): Film {
        return Film(
            name = filmItem.name,
            countries = if (!filmItem.country.contains("Не указано")) filmItem.country.split(", ") else emptyList(),
            budget = if (filmItem.budget.isDigitsOnly()) filmItem.budget.toLong() else 0L,
            description = filmItem.description,
            directors = if (!filmItem.directors.contains("Не указано")) filmItem.directors.split(
                ", "
            ) else emptyList(),
            genres = if (!filmItem.genres.contains("Не указано")) filmItem.genres.split(
                ", "
            ) else emptyList(),
            year = if (filmItem.year.isDigitsOnly()) filmItem.year.toInt() else 0,
            imageUri = filmItem.imageUri.toFormattedString(),
            isFavourite = filmItem.isFavourite
        )
    }

}