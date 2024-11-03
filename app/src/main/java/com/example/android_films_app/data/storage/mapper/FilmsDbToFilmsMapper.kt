package com.example.android_films_app.data.storage.mapper

import com.example.android_films_app.data.storage.entity.FilmDb
import com.example.android_films_app.domain.entity.Film
import javax.inject.Inject

/**
 * @author Lapoushko
 * Маппер для фильмов из бд в фильмы domain
 */
interface FilmsDbToFilmsMapper {
    /**
     * @param filmsDb список фильмов бд
     * @return перевод в список фильмов domain
     */
    fun invoke(filmsDb: FilmDb): Film

//    /**
//     * @param film фильм из бд
//     * @return фильм из domain
//     */
//    fun invoke(film: FilmDb): Film
}

/**
 * Реализация интерфейса FilmsDbToFilmsMapper
 */
class FilmsDbToFilmsMapperImpl @Inject constructor() : FilmsDbToFilmsMapper {
//    override fun invoke(filmsDb: FilmDb): Film {
//        return filmsDb.map { filmDb ->
//            Film(
//                name = filmDb.name ?: "",
//                countries = filmDb.countries ?: emptyList(),
//                directors = filmDb.directors ?: emptyList(),
//                budget = filmDb.budget ?: 0L,
//                genres = filmDb.genres ?: emptyList(),
//                description = filmDb.description ?: "",
//                imageUri = filmDb.imageUri ?: Uri.EMPTY
//            )
//        }
//    }

    override fun invoke(filmsDb: FilmDb): Film {
        return Film(
            name = filmsDb.name ?: "",
            countries = filmsDb.countries ?: emptyList(),
            directors = filmsDb.directors ?: emptyList(),
            budget = filmsDb.budget ?: 0L,
            genres = filmsDb.genres ?: emptyList(),
            description = filmsDb.description ?: "",
            year = filmsDb.year ?: 0,
            imageUri = filmsDb.imageUri ?: "",
            isFavourite = filmsDb.isFavourite ?: false
        )
    }
}