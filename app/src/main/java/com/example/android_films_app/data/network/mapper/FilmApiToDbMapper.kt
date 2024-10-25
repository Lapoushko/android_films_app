package com.example.android_films_app.data.network.mapper

import com.example.android_films_app.data.network.entity.FilmApi
import com.example.android_films_app.data.storage.entity.FilmDb
import javax.inject.Inject

/**
 * @author Lapoushko
 *  маппер фильмов api к фильмам db
 */
interface FilmApiToDbMapper{
    /**
     * смена типап данных
     * @param filmApi апи фильма
     * @return фильм db
     */
    operator fun invoke(filmApi: FilmApi) : FilmDb
}

/**
 * Реализация одноимённого интерфейса
 */
class FilmApiToDbMapperImpl @Inject constructor() : FilmApiToDbMapper {
    override fun invoke(filmApi: FilmApi): FilmDb {
        return FilmDb(
            id = filmApi.id,
            name = filmApi.name,
            description = filmApi.description,
            country = filmApi.country,
            directors = filmApi.directors,
            budget = filmApi.budget,
            genres = filmApi.genres,
            imageUri = filmApi.imageUri
        )
    }
}