package com.example.android_films_app.domain.usecase

import com.example.android_films_app.data.storage.repository.FilmsDataRepository
import com.example.android_films_app.domain.entity.Film
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @author Lapoushko
 * Юзкейс фильма
 */
interface SubscribeFilmUseCase{
    suspend fun getFilm(id: Long) : Flow<Film>
}

/**
 * Реализация одноименного юзкейса
 * @param filmsDataRepository репозиторий
 */
class SubscribeFilmUseCaseImpl @Inject constructor(
    val filmsDataRepository: FilmsDataRepository
): SubscribeFilmUseCase {
    override suspend fun getFilm(id: Long): Flow<Film> {
        return filmsDataRepository.getFilm(id = id)
    }
}