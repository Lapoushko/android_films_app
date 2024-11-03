package com.example.android_films_app.data.storage.dao

import android.util.Log
import com.example.android_films_app.data.storage.data.ExampleLocalData
import com.example.android_films_app.data.storage.entity.FilmDb
import com.example.android_films_app.util.Constants
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
    suspend fun getFilms(query: String): List<FilmDb>

    /**
     * Вставка нового фильма
     */
    suspend fun insertFilm(filmDb: FilmDb)

    /**
     * Удалить фильм
     */
    suspend fun deleteFilm(filmDb: FilmDb)

//    /**
//     * Фильмы по айди
//     * @param id айди фильма
//     * @return фильм
//     */
//    suspend fun getFilm(id: Long): Flow<FilmDb>
}

/**
 * Пример реализации
 */
class FilmsDaoImpl @Inject constructor() : FilmsDao {
    private val films = ExampleLocalData().films

    override suspend fun getFilms(query: String): List<FilmDb> {
        return films
    }

    override suspend fun insertFilm(filmDb: FilmDb) {
        films.add(filmDb)
    }

    /**
     * Удалить фильм
     */
    override suspend fun deleteFilm(filmDb: FilmDb) {
        Log.d(Constants.LOG_KEY, (films[0] == filmDb).toString())
        films.remove(filmDb)
    }
}