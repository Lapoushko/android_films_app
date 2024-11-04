package com.example.android_films_app.data.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android_films_app.data.storage.entity.FilmDb

/**
 * @author Lapoushko
 *
 * интерфейс для работы с бд(пример)
 */
@Dao
interface FilmsDao {
    /**
     * Получить все фильмы
     * @return все фильмы
     */
    @Query("SELECT * FROM films")
    suspend fun getFilms( ): List<FilmDb>

    /**
     * Вставка нового фильма
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilm(filmDb: FilmDb)

    /**
     * Удалить фильм
     */
    @Query("DELETE FROM films WHERE name = :name AND description = :description")
    suspend fun deleteFilm(name: String, description: String)
}

///**
// * Пример реализации
// */
//class FilmsDaoImpl @Inject constructor() : FilmsDao {
//    private val films = ExampleLocalData().films
//
//    override suspend fun getFilms(query: String): List<FilmDb> {
//        return films
//    }
//
//    override suspend fun insertFilm(filmDb: FilmDb) {
//        films.add(filmDb)
//    }
//
//    /**
//     * Удалить фильм
//     */
//    override suspend fun deleteFilm(filmDb: FilmDb) {
//        Log.d(Constants.LOG_KEY, (films[0] == filmDb).toString())
//        films.remove(filmDb)
//    }
//}