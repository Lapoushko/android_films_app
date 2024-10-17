package com.example.android_films_app.presentation.mapper

import com.example.android_films_app.domain.entity.Film
import com.example.android_films_app.presentation.model.FilmItem
import javax.inject.Inject

/**
 * @author Lapoushko
 */
interface FilmToUiItemMapper{
    operator fun invoke(film: Film): FilmItem
}

class FilmToUiItemMapperImpl @Inject constructor(): FilmToUiItemMapper {
    override fun invoke(film: Film): FilmItem {
        return FilmItem(
            name = film.name,
            country = film.country,
            directors = film.directors,
            budget = film.budget,
            genres = film.genres,
            description = film.description,
            imageUri = film.imageUri
        )
    }
}