package com.example.android_films_app.data.network.repository

import com.example.android_films_app.data.network.mapper.FilmResponseToDbMapper
import com.example.android_films_app.data.network.mapper.FilmRetrofitToResponseMapper
import com.example.android_films_app.data.network.service.FilmService
import com.example.android_films_app.data.network.util.InternetChecker
import com.example.android_films_app.data.storage.dao.FilmsDao
import com.example.android_films_app.data.storage.mapper.FilmsDbToFilmsMapper
import com.example.android_films_app.domain.entity.Film
import com.example.android_films_app.domain.repository.FilmsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
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
    private val internetChecker: InternetChecker
) : FilmsRepository {
    private val isNetworkAvailable = MutableStateFlow(false)

    override suspend fun getFilms(): Flow<List<Film>> {
        return withContext(Dispatchers.IO) {
            getFilmsByName(page = 1, limit = 20, nameSearch = "Форсаж")
        }
    }

    private suspend fun getFilmsByName(
        page: Int = 1,
        limit: Int = 5,
        nameSearch: String
    ) : Flow<List<Film>>{
        return withContext(Dispatchers.IO) {
            val listFilmRetrofit = filmService.getFilmsByName(
                page = page,
                limit = limit,
                nameSearch = nameSearch
            )
            val listFilmApi =
                listFilmRetrofit.docs?.map { filmRetrofit ->
                    filmRetrofitMapper.invoke(filmRetrofit = filmRetrofit)
                }
            listFilmApi?.forEach { filmApi ->
                filmsDao.insertFilm(filmResponseToDbMapper.invoke(filmResponse = filmApi))
            }
            flow {
                emit(filmDbToFilmMapper.invoke(filmsDb = filmsDao.getFilms().first()))
            }
        }
    }

    override suspend fun getStatusInternet(): Flow<Boolean> {
        return withContext(Dispatchers.IO) {
            isNetworkAvailable.value = internetChecker.isNetworkAvailable()
            isNetworkAvailable
        }
    }

//    override suspend fun getFilm(id: Long): Flow<Film> {
//        return flow {
//            emit(filmDbToFilmMapper.invoke(film = filmsDao.getFilm(id).first()))
//        }
//    }
}