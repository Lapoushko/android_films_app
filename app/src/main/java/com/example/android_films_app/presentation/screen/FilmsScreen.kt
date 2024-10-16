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
import androidx.navigation.compose.rememberNavController
import com.example.android_films_app.presentation.handler.FilmsScreenHandlerImpl
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
    filmsScreenHandler: FilmsScreenHandlerImpl
) {
    val films =
        generateSequence(1)
        { it + 1 }
            .map { index ->
                FilmItem(
                    name = "Фильм - $index",
                    genres = listOf("Хоррор","Комедия"),
//                    description = "Пострадав в результате несчастного случая, богатый аристократ Филипп нанимает в помощники человека, который менее всего подходит для этой работы, – молодого жителя предместья Дрисса, только что освободившегося из тюрьмы. Несмотря на то, что Филипп прикован к инвалидному креслу, Дриссу удается привнести в размеренную жизнь аристократа дух приключений.",
//                    imageUri = "https://avatars.mds.yandex.net/get-kinopoisk-image/1900788/bd3c56b3-3681-4137-9335-b849c42ed6ea/1920x",
                    directors = listOf("Оливье Накаш", "Эрик Толедано"),
                )

            }
            .take(10)
            .toList()

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
            }
        ) { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues)
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
                        onClick = { filmsScreenHandler.onToFilmDetail(film) }
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
            rememberNavController())
    )
}