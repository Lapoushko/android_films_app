package com.example.android_films_app.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.android_films_app.presentation.handler.FilmsScreenHandlerImpl
import com.example.android_films_app.presentation.main.MainViewModel
import com.example.android_films_app.presentation.model.FilmItem
import com.example.android_films_app.presentation.screen.model.ScreenBar
import com.example.android_films_app.presentation.theme.Typography

/**
 * @author Lapoushko
 *
 * Экран с фильмами
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmsScreen(
    filmsScreenHandler: FilmsScreenHandlerImpl,
    viewModel: MainViewModel = hiltViewModel()
) {
    val isNetworkAvailable by viewModel.isNetworkAvailable.collectAsState()
    val films by viewModel.films.collectAsState()

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
            if (isNetworkAvailable) {
                ContentWithInternet(
                    paddingValues = paddingValues,
                    films = films,
                    filmsScreenHandler = filmsScreenHandler
                )
            } else {
                ContentWithoutInternet(paddingValues = paddingValues, viewModel = viewModel)
            }
        }
    }
}

@Composable
private fun ContentWithInternet(
    paddingValues: PaddingValues,
    films: List<FilmItem>,
    filmsScreenHandler: FilmsScreenHandlerImpl
) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
    ) {
        SearchViewer()
        ItemsLoad(
            films = films,
            filmsScreenHandler = filmsScreenHandler
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchViewer() {
    var text by remember { mutableStateOf("") } // Query for SearchBar
    var active by remember { mutableStateOf(false) } // Active state for SearchBar
    val searchHistory = remember { mutableStateListOf("") }

    Column(
        modifier = Modifier
            .padding(horizontal = 10.dp)
    ) {
        SearchBar(modifier = Modifier.fillMaxWidth(),
            query = text,
            onQueryChange = {
                text = it
            },
            onSearch = {
                searchHistory.add(text)
                active = true
                text = ""
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

@Composable
private fun ItemsLoad(
    films: List<FilmItem>,
    filmsScreenHandler: FilmsScreenHandlerImpl
) {
    LazyColumn(
    ) {
        items(films) { film ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 10.dp,
                        top = 15.dp,
                        end = 10.dp,
                        bottom = 15.dp
                    ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ),
                onClick = {
                    filmsScreenHandler.onToFilmDetail(film)
                }
            ) {
                Column {
                    Text(
                        modifier = Modifier
                            .padding(20.dp),
                        text = film.name,
                        style = Typography.bodyLarge,
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun ContentWithoutInternet(
    paddingValues: PaddingValues,
    viewModel: MainViewModel
) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
    ) {
        Text(
            text = "Нет интернета"
        )
        Icon(
            imageVector = Icons.Default.Refresh,
            contentDescription = null,
            Modifier.clickable { viewModel.onReloadClick() }
        )
    }

}

@Preview(showBackground = true)
@Composable
private fun ContentWithInternetPreview() {
    ContentWithInternet(
        paddingValues = PaddingValues(0.dp),
        films = listOf(),
        filmsScreenHandler = FilmsScreenHandlerImpl(
            rememberNavController()
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun ContentWithoutInternetPreview() {
    ContentWithoutInternet(PaddingValues(0.dp), viewModel = hiltViewModel())
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