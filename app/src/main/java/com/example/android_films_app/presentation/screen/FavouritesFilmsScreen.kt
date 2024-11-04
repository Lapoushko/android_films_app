package com.example.android_films_app.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.android_films_app.presentation.handler.FilmScreenHandler
import com.example.android_films_app.presentation.handler.FilmsScreenHandlerImpl
import com.example.android_films_app.presentation.model.FilmItem
import com.example.android_films_app.presentation.screen.model.ScreenBar
import com.example.android_films_app.presentation.theme.Typography
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

@Composable
private fun ItemsLoad(
    films: List<FilmItem>,
    viewModel: ViewModel,
    filmsScreenHandler: FilmScreenHandler
) {
    LazyColumn {
        items(films) { film ->
            val isFavourite = remember {
                mutableStateOf(film.isFavourite)
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                onClick = { filmsScreenHandler.onToFilmDetail(film) }
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    AsyncImage(
                        model = film.imageUri,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(500.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = film.name,
                        style = Typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    IconButton(onClick = {
                        val newFavouriteStatus = !film.isFavourite
                        (viewModel as FavouritesFilmsScreenViewModel).clickFavourite(
                            filmItem = film.copy(isFavourite = newFavouriteStatus),
                            isFavourite = newFavouriteStatus
                        )
                    }) {
                        val imageVector = if (film.isFavourite) {
                            Icons.Outlined.Favorite
                        } else {
                            Icons.Outlined.FavoriteBorder
                        }
                        Icon(
                            imageVector = imageVector,
                            contentDescription = null
                        )
                    }
                }
            }
        }
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