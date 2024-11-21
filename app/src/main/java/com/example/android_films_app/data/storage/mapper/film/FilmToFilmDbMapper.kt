package com.example.android_films_app.data.storage.mapper.film

import com.example.android_films_app.data.storage.entity.FilmDb
import com.example.android_films_app.domain.entity.Film
import javax.inject.Inject

/**
 * @author Lapoushko
 * маппер фильма domain в db
 */
interface FilmToFilmDbMapper {
    /**
     * @param film фильм domain
     * @return FilmDb
     */
    operator fun invoke(film: Film) : FilmDb
}

class FilmToFilmDbMapperImpl @Inject constructor() : FilmToFilmDbMapper {
    override fun invoke(film: Film): FilmDb {
        return FilmDb(
            id = null,
            name = film.name,
            countries = film.countries,
            directors = film.directors,
            budget = film.budget,
            genres = film.genres,
            description = film.description,
            year = film.year,
            imageUri = film.imageUri,
            isFavourite = film.isFavourite
        )
    }

}