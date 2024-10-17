package com.example.android_films_app.data.storage.dao

import com.example.android_films_app.data.storage.data.ExampleLocalData
import com.example.android_films_app.data.storage.entity.FilmDb
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * @author Lapoushko
 *
 * интерфейс для работы с бд(пример)
 */
interface FilmsDao {
    /**
     * Получить все фильмы
     * @return все фильмы
     */
    suspend fun getFilms(): Flow<List<FilmDb>>

    /**
     * Фильмы по айди
     * @param id айди фильма
     * @return фильм
     */
    suspend fun getFilm(id: Long): Flow<FilmDb>
}

/**
 * Пример реализации
 */
class FilmsDaoImpl @Inject constructor() : FilmsDao {
    private val exampleLocalData = ExampleLocalData()

    override suspend fun getFilms(): Flow<List<FilmDb>> {
        return flow {
            exampleLocalData.films
            emit(exampleLocalData.films)
        }
    }

    override suspend fun getFilm(id: Long): Flow<FilmDb> {
        return flow {
            val films = exampleLocalData.films
            val foundFilm = films.find { it.id == id }
            if (foundFilm != null) {
                emit(foundFilm)
            }
        }
    }
}