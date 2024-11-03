package com.example.android_films_app.domain.repository

import com.example.android_films_app.domain.entity.Film
import kotlinx.coroutines.flow.Flow

/**
 * @author Lapoushko
 *
 * Репозиторий фильмов
 */
interface FilmsRepository{
    /**
     * Получить все фильмы
     * @return Flow всех фильмов domain
     */
    suspend fun getFilms(query: String): Flow<List<Film>>

    /**
     * получить статут интернета
     * @return текущий статус
     */
    suspend fun getStatusInternet(): Boolean

//    /**
//     * Получить фильм domain
//     * @return Flow фильма domain
//     */
//    suspend fun getFilm(id: Long): Flow<Film>
}
