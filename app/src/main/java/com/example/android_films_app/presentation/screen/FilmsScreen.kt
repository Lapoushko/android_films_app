package com.example.android_films_app.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.android_films_app.presentation.handler.FilmScreenHandler
import com.example.android_films_app.presentation.handler.FilmsScreenHandlerImpl
import com.example.android_films_app.presentation.model.FilmItem
import com.example.android_films_app.presentation.screen.model.ScreenBar
import com.example.android_films_app.presentation.theme.Typography
import com.example.android_films_app.presentation.viewModel.MainViewModel

/**
 * @author Lapoushko
 *
 * Экран с фильмами
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmsScreen(
    filmsScreenHandler: FilmScreenHandler,
    viewModel: MainViewModel = hiltViewModel()
) {
    val isNetworkAvailable by viewModel.isNetworkAvailable.collectAsState()
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
                if (isNetworkAvailable) {
                    ContentWithInternet(
                        films = films,
                        viewModel = viewModel,
                        filmsScreenHandler = filmsScreenHandler
                    )
                } else {
                    ContentWithoutInternet(
                        viewModel = viewModel,
                        query = textSearch.value
                    )
                }
            }

        }
    }
}

@Composable
private fun ContentWithInternet(
    films: List<FilmItem>,
    viewModel: MainViewModel,
    filmsScreenHandler: FilmScreenHandler,
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchViewer(
    onStringChanged: (String) -> Unit,
) {
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    val searchHistory = remember { mutableStateListOf("") }

    Column(
        modifier = Modifier
            .padding(horizontal = 10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            SearchBar(modifier = Modifier.fillMaxWidth(),
                query = text,
                onQueryChange = {
                    text = it
                },
                onSearch = {
                    searchHistory.add(text)
                    active = false
                    onStringChanged(text)
                },
                active = active,
                onActiveChange = {
                    active = it
                },
                placeholder = {
                    Text(text = "Название фильма")
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "")
                },
                trailingIcon = {
                    if (active) {
                        Icon(
                            modifier = Modifier.clickable {
                                if (text.isNotEmpty()) {
                                    text = ""
                                } else {
                                    active = false
                                }
                            },
                            imageVector = Icons.Default.Close,
                            contentDescription = ""
                        )
                    }
                }
            ) {
                searchHistory.forEach {
                    if (it.isNotEmpty()) {
                        Row(
                            modifier = Modifier.padding(all = 14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(onClick = { text = it }) {
                                Icon(imageVector = Icons.Default.History, contentDescription = null)
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(text = it)
                        }
                    }
                }

                HorizontalDivider()
                Text(
                    modifier = Modifier
                        .padding(all = 14.dp)
                        .fillMaxWidth()
                        .clickable {
                            searchHistory.clear()
                        },
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    text = "Очистить историю поиска"
                )
            }
        }
    }
}

@Composable
private fun ItemsLoad(
    films: List<FilmItem>,
    viewModel: MainViewModel,
    filmsScreenHandler: FilmScreenHandler
) {
    LazyColumn {
        items(films) { film ->
            var isFavourite = film.isFavourite
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
                        isFavourite = !isFavourite
                        viewModel.clickFavourite(filmItem = film, isFavourite = isFavourite)
                    }) {
                        val imageVector = if (isFavourite) {
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

@Composable
private fun ContentWithoutInternet(
    viewModel: MainViewModel,
    query: String
) {
    Column(
        modifier = Modifier
    ) {
        Text(
            text = "Нет интернета"
        )
        Icon(
            imageVector = Icons.Default.Refresh,
            contentDescription = null,
            Modifier.clickable { viewModel.onReloadClick(query) }
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
private fun ContentWithoutInternetPreview() {
    ContentWithoutInternet(
//        PaddingValues(0.dp),
        viewModel = hiltViewModel(),
        ""
    )
}

@Preview(showBackground = true)
@Composable
fun FilmsScreenPreview() {
    FilmsScreen(
        filmsScreenHandler = FilmsScreenHandlerImpl(
            rememberNavController()
        )
    )
}