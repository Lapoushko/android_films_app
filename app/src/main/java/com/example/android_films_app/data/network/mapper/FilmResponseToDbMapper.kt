package com.example.android_films_app.data.network.mapper

import com.example.android_films_app.data.network.entity.FilmResponse
import com.example.android_films_app.data.storage.entity.FilmDb
import javax.inject.Inject

/**
 * @author Lapoushko
 *  маппер фильмов api к фильмам db
 */
interface FilmResponseToDbMapper{
    /**
     * смена типап данных
     * @param filmResponse апи фильма
     * @return фильм db
     */
    operator fun invoke(filmResponse: FilmResponse) : FilmDb
}

/**
 * Реализация одноимённого интерфейса
 */
class FilmResponseToDbMapperImpl @Inject constructor() : FilmResponseToDbMapper {
    override fun invoke(filmResponse: FilmResponse): FilmDb {
        return FilmDb(
            id = filmResponse.id,
            name = filmResponse.name,
            description = filmResponse.description,
            country = filmResponse.country,
            directors = filmResponse.directors,
            budget = filmResponse.budget,
            genres = filmResponse.genres,
            imageUri = filmResponse.imageUri
        )
    }
}