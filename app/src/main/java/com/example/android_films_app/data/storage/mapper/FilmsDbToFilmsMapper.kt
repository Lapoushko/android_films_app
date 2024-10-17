package com.example.android_films_app.data.storage.mapper

import com.example.android_films_app.data.storage.entity.FilmDb
import com.example.android_films_app.domain.entity.Film
import javax.inject.Inject

/**
 * @author Lapoushko
 */
interface FilmsDbToFilmsMapper {
    fun invoke(filmsDb: List<FilmDb>): List<Film>

    fun invoke(film: FilmDb): Film
}

class FilmsDbToFilmsMapperImpl @Inject constructor() : FilmsDbToFilmsMapper {
    override fun invoke(filmsDb: List<FilmDb>): List<Film> {
        return filmsDb.map { filmDb ->
            Film(
                name = filmDb.name,
                country = filmDb.country,
                directors = filmDb.directors,
                budget = filmDb.budget,
                genres = filmDb.genres,
                description = filmDb.description,
                imageUri = filmDb.imageUri
            )
        }
    }

    override fun invoke(filmDb: FilmDb): Film {
        return Film(
            name = filmDb.name,
            country = filmDb.country,
            directors = filmDb.directors,
            budget = filmDb.budget,
            genres = filmDb.genres,
            description = filmDb.description,
            imageUri = filmDb.imageUri
        )
    }
}