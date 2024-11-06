package com.example.android_films_app.domain.usecase.storage.room

import com.example.android_films_app.domain.entity.Film
import com.example.android_films_app.domain.repository.FilmsRepository
import javax.inject.Inject

/**
 * @author Lapoushko
 * Юзкейс подписки на избранный фильм
 */
interface SubscribeFavouriteFilm {
    /**
     * Вставка фильма
     */
    suspend fun insert(film: Film)

    /**
     * удаление фильма
     */
    suspend fun delete(film: Film)
}

class SubscribeFavouriteFilmImpl @Inject constructor(
    private val repository: FilmsRepository
) : SubscribeFavouriteFilm {
    /**
     * Вставка фильма
     */
    override suspend fun insert(film: Film) {
        repository.insertFilm(
            Film(
                name = film.name,
                countries = film.countries,
                directors = film.directors,
                budget = film.budget,
                genres = film.genres,
                description = film.description,
                year = film.year,
                imageUri = film.imageUri,
                isFavourite = true
            )
        )
    }

    /**
     * удаление фильма
     */
    override suspend fun delete(film: Film) {
        repository.deleteFilm(film = Film(
            name = film.name,
            countries = film.countries,
            directors = film.directors,
            budget = film.budget,
            genres = film.genres,
            description = film.description,
            year = film.year,
            imageUri = film.imageUri,
            isFavourite = true
        ))
    }

}