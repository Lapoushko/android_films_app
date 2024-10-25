package com.example.android_films_app.data.storage.mapper

import android.net.Uri
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
    fun invoke(filmsDb: List<FilmDb>): List<Film>

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
    override fun invoke(filmsDb: List<FilmDb>): List<Film> {
        return filmsDb.map { filmDb ->
            Film(
                name = filmDb.name ?: "Название не указано",
                country = filmDb.country ?: "Страна не указана",
                directors = filmDb.directors ?: emptyList(),
                budget = filmDb.budget ?: 0L,
                genres = filmDb.genres ?: emptyList(),
                description = filmDb.description ?: "Описание не указано",
                imageUri = filmDb.imageUri ?: Uri.parse("")
            )
        }
    }

//    override fun invoke(filmDb: FilmDb): Film {
//        return Film(
//            name = filmDb.name,
//            country = filmDb.country,
//            directors = filmDb.directors,
//            budget = filmDb.budget,
//            genres = filmDb.genres,
//            description = filmDb.description,
//            imageUri = filmDb.imageUri
//        )
//    }
}