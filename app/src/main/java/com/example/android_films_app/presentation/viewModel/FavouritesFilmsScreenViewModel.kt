package com.example.android_films_app.presentation.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_films_app.domain.repository.PreferencesRepository
import com.example.android_films_app.domain.usecase.storage.room.SubscribeFavouriteFilm
import com.example.android_films_app.domain.usecase.storage.room.SubscribeGetFavouriteFilmUseCase
import com.example.android_films_app.presentation.mapper.film.FilmItemToFilmMapper
import com.example.android_films_app.presentation.mapper.film.FilmToUiItemMapper
import com.example.android_films_app.presentation.model.FilmItem
import com.example.android_films_app.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * view model для избранных фильмов
 * @author Lapoushko
 */
@HiltViewModel
class FavouritesFilmsScreenViewModel @Inject constructor(
    private val subscribeGetFavouriteFilm: SubscribeGetFavouriteFilmUseCase,
    private val subscribeFavouriteFilm: SubscribeFavouriteFilm,
    private val domainMapper: FilmItemToFilmMapper,
    private val uiMapper: FilmToUiItemMapper,
    preferencesRepository: PreferencesRepository
) : ViewModel() {
    private val _films: MutableStateFlow<List<FilmItem>> = MutableStateFlow(emptyList())
    val films: StateFlow<List<FilmItem>> = _films.asStateFlow()

    /**
     * Получить все фильмы
     */
    init {
        Log.d(Constants.LOG_KEY, "FavouritesFilmsScreenViewModel init")
        load("")
    }

    private fun load(query: String) {
        viewModelScope.launch {
            _films.value = subscribeGetFavouriteFilm.getFilms(query = query)
                .map { film -> uiMapper.invoke(film) }
        }
    }

    fun onReloadClick(query: String) {
        load(query = query)
    }

    override fun onCleared() {
        Log.d(Constants.LOG_KEY, "FavouritesFilmsScreenViewModel cleared")
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
            //TODO пофиксить запрос при удалении
            load("")
        }
    }

    fun saveSearch(history: List<String>){

    }
}