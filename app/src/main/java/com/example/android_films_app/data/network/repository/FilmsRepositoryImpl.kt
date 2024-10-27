package com.example.android_films_app.data.network.repository

import com.example.android_films_app.data.network.mapper.FilmApiToDbMapper
import com.example.android_films_app.data.network.mapper.FilmRetrofitToApiMapper
import com.example.android_films_app.data.network.service.FilmService
import com.example.android_films_app.data.network.util.InternetChecker
import com.example.android_films_app.data.storage.dao.FilmsDao
import com.example.android_films_app.data.storage.mapper.FilmsDbToFilmsMapper
import com.example.android_films_app.domain.entity.Film
import com.example.android_films_app.domain.repository.FilmsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
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
    private val filmRetrofitMapper: FilmRetrofitToApiMapper,
    private val filmApiToDbMapper: FilmApiToDbMapper,
    private val filmDbToFilmMapper: FilmsDbToFilmsMapper,
    private val internetChecker: InternetChecker
) : FilmsRepository {
    private val isNetworkAvailable = MutableStateFlow(false)

    override suspend fun getFilms(): Flow<List<Film>> {
        val filmRetrofit =
//                filmService.getFilmsByName(
//                    page = 1,
//                    limit = 1,
//                    nameSearch = "Форсаж"
//                )
            filmService.getFilmById(id = 666)
        val filmApi = filmRetrofitMapper.invoke(filmRetrofit = filmRetrofit)
        filmsDao.insertFilm(
            filmApiToDbMapper.invoke(
                filmApi = filmApi
            )
        )
        return flow {
            emit(filmDbToFilmMapper.invoke(filmsDb = filmsDao.getFilms().first()))
        }
    }

    override suspend fun getStatusInternet(): Flow<Boolean> {
        isNetworkAvailable.value = internetChecker.isNetworkAvailable()
        return isNetworkAvailable
    }

//    override suspend fun getFilm(id: Long): Flow<Film> {
//        return flow {
//            emit(filmDbToFilmMapper.invoke(film = filmsDao.getFilm(id).first()))
//        }
//    }
}