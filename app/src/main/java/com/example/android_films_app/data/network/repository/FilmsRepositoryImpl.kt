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

    override suspend fun getFilms(query: String): List<Film> {
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
    ): List<Film> {
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

            //если в бд уже есть этот фильм, то заменять его копией с бд
            val listFromDao = getFilmsFromDao(query = nameSearch)
            filmsResponse?.map { filmResponse ->
                val filmDb = filmResponseToDbMapper.invoke(filmResponse = filmResponse)
                listFromDao.find {
                    it.description == filmDb.description && it.name == filmDb.name
                } ?: filmDbToFilmMapper.invoke(filmDb)
            } ?: emptyList()
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
        withContext(Dispatchers.IO) {
            filmsDao.insertFilm(filmToFilmDbMapper(film))
        }
    }

    /**
     * Удалить фильм
     */
    override suspend fun deleteFilm(film: Film) {
        withContext(Dispatchers.IO) {
            val filmDb = filmToFilmDbMapper(film = film)
            filmsDao.deleteFilm(
                name = filmDb.name ?: "",
                description = filmDb.description ?: ""
            )
        }
    }

    /**
     * Фильмы из дао
     */
    override suspend fun getFilmsFromDao(query: String): List<Film> {
        var films = emptyList<Film>()
        withContext(Dispatchers.IO) {
            val filmsDb = filmsDao.getFilms(query)
            films = filmsDb.map { filmDb -> filmDbToFilmMapper.invoke(filmsDb = filmDb) }
        }
        return films
    }
}