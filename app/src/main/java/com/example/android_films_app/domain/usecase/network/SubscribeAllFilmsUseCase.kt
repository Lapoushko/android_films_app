package com.example.android_films_app.domain.usecase.network

import com.example.android_films_app.domain.entity.Film
import com.example.android_films_app.domain.repository.FilmsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * @author Lapoushko
 *
 * Юзкейс всех фильмов
 */
interface SubscribeAllFilmsUseCase {
    /**
     * Получить все фильмы domain
     * @return Flow списка фильмов
     */
    suspend fun getFilms(query: String): Flow<List<Film>>
}

/**
 * Реализация одноименного юзкейса
 * @param filmsRepository репозиторий с фильмами
 */
class SubscribeAllFilmsUseCaseImpl @Inject constructor(
    val filmsRepository: FilmsRepository
) : SubscribeAllFilmsUseCase {
    override suspend fun getFilms(query: String): Flow<List<Film>> {
        return filmsRepository.getFilms(query).map { films ->
            films.ifEmpty {
                emptyList()
            }
        }
    }
}