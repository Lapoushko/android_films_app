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

    /**
     * Вставить фильм в базу данных
     */
    suspend fun insertFilm(film: Film)

    /**
     * Удалить фильм
     */
    suspend fun deleteFilm(film: Film)

    /**
     * Фильмы из дао
     */
    suspend fun getFilmsFromDao(query: String) : List<Film>
}
