package com.example.android_films_app.presentation.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_films_app.domain.usecase.network.SubscribeAllFilmsUseCase
import com.example.android_films_app.domain.usecase.network.SubscribeCheckInternetUseCase
import com.example.android_films_app.domain.usecase.storage.preference.SubscribeClearQueriesUseCase
import com.example.android_films_app.domain.usecase.storage.preference.SubscribeGetQueriesUseCase
import com.example.android_films_app.domain.usecase.storage.preference.SubscribeSetQueriesUseCase
import com.example.android_films_app.domain.usecase.storage.room.SubscribeFavouriteFilm
import com.example.android_films_app.presentation.mapper.film.FilmItemToFilmMapper
import com.example.android_films_app.presentation.mapper.film.FilmToUiItemMapper
import com.example.android_films_app.presentation.model.FilmItem
import com.example.android_films_app.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Lapoushko
 * Вью модель для работы с экраном фильма
 * @param subscribeAllFilmsUseCase юзкейс всех фильмов
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val subscribeAllFilmsUseCase: SubscribeAllFilmsUseCase,
    private val subscribeFavouriteFilm: SubscribeFavouriteFilm,
    private val subscribeCheckInternetUseCase: SubscribeCheckInternetUseCase,
    private val subscribeGetQueriesUseCase: SubscribeGetQueriesUseCase,
    private val subscribeSetQueriesUseCase: SubscribeSetQueriesUseCase,
    private val subscribeClearQueriesUseCase: SubscribeClearQueriesUseCase,
    private val domainMapper: FilmItemToFilmMapper,
    private val uiMapper: FilmToUiItemMapper
) : ViewModel() {
    private val _films: MutableStateFlow<List<FilmItem>> = MutableStateFlow(emptyList())
    val films: StateFlow<List<FilmItem>> = _films.asStateFlow()

    private val _isNetworkAvailable = MutableStateFlow(false)
    val isNetworkAvailable: StateFlow<Boolean> = _isNetworkAvailable.asStateFlow()

    private val _queries: MutableStateFlow<List<String>> = MutableStateFlow(emptyList())
    val queries: StateFlow<List<String>> = _queries

    private var loadJob: Job? = null

    /**
     * Получить все фильмы
     */
    init {
        Log.d(Constants.LOG_KEY, "MainViewModel init")
        viewModelScope.launch {
            loadQueries()
            load(_queries.value.lastOrNull() ?: "")
        }
    }

    override fun onCleared() {
        Log.d(Constants.LOG_KEY, "MainViewModel cleared")
    }

    fun onReloadClick(queries: List<String>) {
        load(query = queries.lastOrNull() ?: "")
        viewModelScope.launch {
            subscribeSetQueriesUseCase.saveQueries(queries)
        }
    }

    /**
     * подписка или отписка от избранного фильма
     */
    fun clickFavourite(filmItem: FilmItem, isFavourite: Boolean) {
        viewModelScope.launch {
            if (isFavourite) {
                subscribeFavouriteFilm.insert(domainMapper(filmItem = filmItem))
            } else {
                subscribeFavouriteFilm.delete(domainMapper(filmItem = filmItem))
            }
            updateFilmFavouriteStatus(filmItem, isFavourite)
        }
    }

    fun deleteHistory(){
        viewModelScope.launch {
            subscribeClearQueriesUseCase.clear()
//            _queries.value = subscribeGetQueriesUseCase.getQueries().first()
        }
    }

    private fun load(query: String) {
        loadJob?.cancel()
        loadJob = viewModelScope.launch {
            _isNetworkAvailable.value = subscribeCheckInternetUseCase.getStatusInternet()
            if (isNetworkAvailable.value) {
                _films.value = subscribeAllFilmsUseCase.getFilms(query).map { uiMapper(it) }
            }
        }
    }

    private suspend fun loadQueries(){
        _queries.value = subscribeGetQueriesUseCase.getQueries().first()
    }

    private fun updateFilmFavouriteStatus(filmItem: FilmItem, isFavourite: Boolean) {
        _films.value = films.value.map { film ->
            if (film == filmItem) {
                film.copy(isFavourite = isFavourite)
            } else {
                film
            }
        }
    }
}