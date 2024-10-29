package com.example.android_films_app.presentation.mapper

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
            name = film.name,
            country = film.country,
            directors = film.directors,
            budget = if (film.budget == 0L) "Не указан" else film.budget.toString(),
            genres = film.genres,
            description = film.description,
            imageUri = film.imageUri.toString()
                .toFormattedUri() // костыль для того, чтобы не ломался маршрут к новому экрану /* TODO починить
        )
    }
}