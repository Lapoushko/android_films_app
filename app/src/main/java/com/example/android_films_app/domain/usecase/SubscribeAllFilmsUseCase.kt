package com.example.android_films_app.domain.usecase

import com.example.android_films_app.data.storage.repository.FilmsDataRepository
import com.example.android_films_app.domain.entity.Film
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * @author Lapoushko
 */

interface SubscribeAllFilmsUseCase {
    suspend fun getFilms(): Flow<List<Film>>
}

class SubscribeAllFilmsUseCaseImpl @Inject constructor(
    val filmsDataRepository: FilmsDataRepository
) : SubscribeAllFilmsUseCase {
    override suspend fun getFilms(): Flow<List<Film>> {
        return filmsDataRepository.getFilms().map { films ->
            films.ifEmpty {
                emptyList()
            }
        }
    }
}