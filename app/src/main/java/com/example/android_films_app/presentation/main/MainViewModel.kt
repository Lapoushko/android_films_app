package com.example.android_films_app.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_films_app.domain.usecase.SubscribeAllFilmsUseCase
import com.example.android_films_app.domain.usecase.SubscribeFilmUseCase
import com.example.android_films_app.presentation.mapper.FilmToUiItemMapper
import com.example.android_films_app.presentation.model.FilmItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
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
    private val subscribeFilmUseCase: SubscribeFilmUseCase,
    val uiMapper: FilmToUiItemMapper
) : ViewModel() {
    private val _films: MutableStateFlow<List<FilmItem>> = MutableStateFlow(emptyList())
    val films: StateFlow<List<FilmItem>> = _films.asStateFlow()

    private val _film: MutableStateFlow<FilmItem?> = MutableStateFlow(null)
    val film: StateFlow<FilmItem?> = _film.asStateFlow()

    /**
     * Получить все фильмы
     */
    init {
        viewModelScope.launch {
            _films.value =
                subscribeAllFilmsUseCase
                    .getFilms()
                    .first()
                    .map { uiMapper(it) }
        }
    }

    /**
     * Получить информацию о фильме
     *
     * P.S. Функция пока не используется. думаю понадобится в будущем
     * @param id айди фильма
     */
    fun getDetail(id: Long) {
        viewModelScope.launch {
            _film.value =
                uiMapper(
                    subscribeFilmUseCase
                        .getFilm(id = id)
                        .first()
                )
        }
    }
}