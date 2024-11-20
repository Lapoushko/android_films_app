package com.example.android_films_app.presentation.mapper.film

import com.example.android_films_app.domain.entity.Film
import com.example.android_films_app.presentation.extension.toFormattedUri
import com.example.android_films_app.presentation.model.FilmItem
import javax.inject.Inject

/**
 * @author Lapoushko
 * перевод фильмов Domain в FilmItem
 */
interface FilmToUiItemMapper {
    /**
     * Перевод фильмов
     * @param film фильм domain
     * @return film ui
     */
    operator fun invoke(film: Film): FilmItem
}

/**
 * Реализация одноименного интерфейса
 */
class FilmToUiItemMapperImpl @Inject constructor() : FilmToUiItemMapper {
    override fun invoke(film: Film): FilmItem {
        return FilmItem(
            name = film.name.ifEmpty { "Не указано" },
            country = if (film.countries.isEmpty())
                "Не указано"
            else film.countries.joinToString(
                ", "
            ) { it },
            directors = if (film.directors.isEmpty())
                "Не указано"
            else film.directors.joinToString(
                ", "
            ) { it },
            budget = if (film.budget == 0L) "Не указано" else film.budget.toString(),
            genres = if (film.genres.isEmpty())
                "Не указано"
            else film.genres.joinToString(
                ", "
            ) { it },
            description = film.description.ifEmpty { "Не указано" },
            year = film.year.toString().ifEmpty { "Не указано" },
            imageUri = film.imageUri
                .toFormattedUri(), // костыль для того, чтобы не ломался маршрут к новому экрану /* TODO починить
            isFavourite = film.isFavourite
        )
    }
}