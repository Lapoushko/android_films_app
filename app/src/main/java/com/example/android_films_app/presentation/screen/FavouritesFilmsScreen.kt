package com.example.android_films_app.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.rememberNavController
import com.example.android_films_app.presentation.handler.FilmsScreenHandlerImpl
import com.example.android_films_app.presentation.model.FilmItem
import com.example.android_films_app.presentation.screen.model.ScreenBar
import com.example.android_films_app.presentation.viewModel.FavouritesFilmsScreenViewModel

/**
 * @author Lapoushko
 *
 * Экран с фильмами
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouritesFilmsScreen(
    filmsScreenHandler: FilmsScreenHandlerImpl,
    viewModel: FavouritesFilmsScreenViewModel = hiltViewModel()
) {
    val films by viewModel.films.collectAsState()
    val textSearch = remember {
        mutableStateOf("")
    }

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(title = {
                    Text(
                        text = ScreenBar.Films.title ?: "Нет названия"
                    )
                })
            },
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
            ) {
                SearchViewer {
                    textSearch.value = it
                    viewModel.onReloadClick(it)
                }

                ContentWithInternet(
                    films = films,
                    viewModel = viewModel,
                    filmsScreenHandler = filmsScreenHandler
                )
            }
        }
    }
}

@Composable
private fun ContentWithInternet(
    films: List<FilmItem>,
    viewModel: ViewModel,
    filmsScreenHandler: FilmsScreenHandlerImpl,
) {
    Column(
        modifier = Modifier
    ) {
        ItemsLoad(
            films = films,
            viewModel = viewModel,
            filmsScreenHandler = filmsScreenHandler,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ContentWithInternetPreview() {
    ContentWithInternet(
        films = listOf(),
        hiltViewModel(),
        filmsScreenHandler = FilmsScreenHandlerImpl(
            rememberNavController()
        )
    )
}

@Preview(showBackground = true)
@Composable
fun FavouritesScreenPreview() {
    FavouritesFilmsScreen(
        filmsScreenHandler = FilmsScreenHandlerImpl(
            rememberNavController()
        )
    )
}