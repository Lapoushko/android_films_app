package com.example.android_films_app.presentation.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_films_app.domain.usecase.SubscribeAllFilmsUseCase
import com.example.android_films_app.domain.usecase.SubscribeCheckInternetUseCase
import com.example.android_films_app.presentation.mapper.FilmToUiItemMapper
import com.example.android_films_app.presentation.model.FilmItem
import com.example.android_films_app.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Lapoushko
 * Вью модель для работы с экраном фильма
 * @param subscribeFilmUseCase Юзкейс одного фильма
 * @param subscribeAllFilmsUseCase юзкейс всех фильмов
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val subscribeAllFilmsUseCase: SubscribeAllFilmsUseCase,
//    private val subscribeFilmUseCase: SubscribeFilmUseCase,
    private val subscribeCheckInternetUseCase: SubscribeCheckInternetUseCase,
    val uiMapper: FilmToUiItemMapper
) : ViewModel() {
    private val _films: MutableStateFlow<List<FilmItem>> = MutableStateFlow(emptyList())
    val films: StateFlow<List<FilmItem>> = _films.asStateFlow()

    private val _film: MutableStateFlow<FilmItem?> = MutableStateFlow(null)
    val film: StateFlow<FilmItem?> = _film.asStateFlow()

    private val _isNetworkAvailable: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isNetworkAvailable: StateFlow<Boolean> = _isNetworkAvailable.asStateFlow()

    private var loadJob: Job? = null

    /**
     * Получить все фильмы
     */
    init {
        Log.d(Constants.LOG_KEY, "MainViewModel init")
        load()
    }

    private fun load() {
        loadJob?.cancel()
        loadJob = viewModelScope.launch {
            subscribeCheckInternetUseCase.getStatusInternet()
                .collect { isNetworkAvailable ->
                    _isNetworkAvailable.value = isNetworkAvailable
                    if (isNetworkAvailable) {
                        subscribeAllFilmsUseCase.getFilms()
                            .collect { filmList ->
                                _films.value = filmList.map { uiMapper(it) }
                            }
                    }
                }
        }
    }

    fun onReloadClick() {
        load()
    }

    override fun onCleared() {
        Log.d(Constants.LOG_KEY, "MainViewModel cleared")
    }

//    /**
//     * Получить информацию о фильме
//     *
//     * P.S. Функция пока не используется. думаю понадобится в будущем
//     * @param id айди фильма
//     */
//    fun getDetail(id: Long) {
//        viewModelScope.launch {
//            _film.value =
//                uiMapper(
//                    subscribeFilmUseCase
//                        .getFilm(id = id)
//                        .first()
//                )
//        }
//    }
}