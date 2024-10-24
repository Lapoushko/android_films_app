package com.example.android_films_app.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.android_films_app.presentation.handler.FilmsScreenHandlerImpl
import com.example.android_films_app.presentation.main.MainViewModel
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
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues)
            ) {
                items(viewModel.films.value) { film ->
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
    }
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