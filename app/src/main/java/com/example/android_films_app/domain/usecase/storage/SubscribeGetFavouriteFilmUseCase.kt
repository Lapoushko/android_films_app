package com.example.android_films_app.domain.usecase.storage

import com.example.android_films_app.domain.entity.Film
import com.example.android_films_app.domain.repository.FilmsRepository

/**
 * @author Lapoushko
 * избранные фильмы
 */

interface SubscribeGetFavouriteFilmUseCase{
    /**
     * получить все фильбмы
     * @return фильмы
     */
    suspend fun getFilms(query: String) : List<Film>
}

class SubscribeGetFavouriteFilmUseCaseImpl(
    private val repository: FilmsRepository):
    SubscribeGetFavouriteFilmUseCase{
    /**
     * получить все фильбмы
     * @return фильмы
     */
    override suspend fun getFilms(query: String): List<Film> {
        return repository.getFilmsFromDao(query = query)
    }

}