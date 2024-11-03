package com.example.android_films_app.data.network.repository

import com.example.android_films_app.data.network.mapper.FilmResponseToDbMapper
import com.example.android_films_app.data.network.mapper.FilmRetrofitToResponseMapper
import com.example.android_films_app.data.network.service.FilmService
import com.example.android_films_app.data.network.util.InternetChecker
import com.example.android_films_app.data.storage.dao.FilmsDao
import com.example.android_films_app.data.storage.mapper.FilmToFilmDbMapper
import com.example.android_films_app.data.storage.mapper.FilmsDbToFilmsMapper
import com.example.android_films_app.domain.entity.Film
import com.example.android_films_app.domain.repository.FilmsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * @author Lapoushko
 *
 * @param filmsDao дао для фильмов
 * @param filmDbToFilmMapper маппер фильмов
 */
class FilmsRepositoryImpl @Inject constructor(
    private val filmService: FilmService,
    private val filmsDao: FilmsDao,
    private val filmRetrofitMapper: FilmRetrofitToResponseMapper,
    private val filmResponseToDbMapper: FilmResponseToDbMapper,
    private val filmDbToFilmMapper: FilmsDbToFilmsMapper,
    private val filmToFilmDbMapper: FilmToFilmDbMapper,
    private val internetChecker: InternetChecker
) : FilmsRepository {
    private var isNetworkAvailable = false

    override suspend fun getFilms(query: String): Flow<List<Film>> {
        return withContext(Dispatchers.IO) {
            getFilmsByName(page = 1, limit = 20, nameSearch = query)
        }
    }

    /**
     * Получить фильмы по их названию
     */
    private suspend fun getFilmsByName(
        page: Int = 1,
        limit: Int = 5,
        nameSearch: String
    ): Flow<List<Film>> {
        return withContext(Dispatchers.IO) {
            val filmsRetrofit = filmService.getFilmsByName(
                page = page,
                limit = limit,
                nameSearch = nameSearch
            )
            val filmsResponse =
                filmsRetrofit.docs?.map { filmRetrofit ->
                    filmRetrofitMapper.invoke(filmRetrofit = filmRetrofit)
                }?.filter { it.name?.isNotEmpty()!! && it.imageUri?.isNotEmpty()!! }

            flow {
                filmsResponse?.map { filmResponse ->
                    filmDbToFilmMapper.invoke(filmResponseToDbMapper.invoke(filmResponse = filmResponse))
                }?.let { emit(it) } ?: emptyList<Film>()
            }
        }
    }

    override suspend fun getStatusInternet(): Boolean {
        return withContext(Dispatchers.IO) {
            isNetworkAvailable = internetChecker.isNetworkAvailable()
            isNetworkAvailable
        }
    }

    /**
     * Вставить фильм в базу данных
     */
    override suspend fun insertFilm(film: Film) {
        return withContext(Dispatchers.IO) {
            filmsDao.insertFilm(filmToFilmDbMapper(film))
        }
    }

    /**
     * Удалить фильм
     */
    override suspend fun deleteFilm(film: Film) {
        filmsDao.deleteFilm(filmToFilmDbMapper(film = film))
    }

    /**
     * Фильмы из дао
     */
    override suspend fun getFilmsFromDao(query: String): List<Film> {
        return filmsDao.getFilms(query = query)
            .map { filmDb -> filmDbToFilmMapper.invoke(filmsDb = filmDb) }
    }
}