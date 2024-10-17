package com.example.android_films_app.data.storage.repository

import com.example.android_films_app.data.storage.dao.FilmsDao
import com.example.android_films_app.data.storage.mapper.FilmsDbToFilmsMapper
import com.example.android_films_app.domain.entity.Film
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * @author Lapoushko
 *
 * Репозиторий фильмов
 */
interface FilmsDataRepository{
    /**
     * Получить все фильмы
     * @return Flow всех фильмов domain
     */
    suspend fun getFilms(): Flow<List<Film>>

    /**
     * Получить фильм domain
     * @return Flow фильма domain
     */
    suspend fun getFilm(id: Long): Flow<Film>
}

/**
 * @param filmsDao дао для фильмов
 * @param filmDbToFilmMapper маппер фильмов
 */
class FilmsDataRepositoryImpl @Inject constructor(
    val filmsDao: FilmsDao,
    val filmDbToFilmMapper: FilmsDbToFilmsMapper
) : FilmsDataRepository{
    override suspend fun getFilms(): Flow<List<Film>> {
        return flow {
            emit(filmDbToFilmMapper.invoke(filmsDb = filmsDao.getFilms().first()))
        }
    }

    override suspend fun getFilm(id: Long): Flow<Film> {
        return flow {
            emit(filmDbToFilmMapper.invoke(film = filmsDao.getFilm(id).first()))
        }
    }
}