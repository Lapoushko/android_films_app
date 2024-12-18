package com.example.android_films_app.presentation.screen

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.android_films_app.presentation.handler.FilmDetailScreenHandlerImpl
import com.example.android_films_app.presentation.model.FilmItem
import com.example.android_films_app.presentation.theme.Typography

/**
 * @author Lapoushko
 * детали фильма
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmDetailsScreen(
    filmDetailScreenHandler: FilmDetailScreenHandlerImpl,
    film: FilmItem
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = film.name
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { filmDetailScreenHandler.onToBack() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    },
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = Color.Yellow,
                    )
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .verticalScroll(rememberScrollState())
                    .padding(innerPadding)
            ) {
                FilmPreviewImage(imageUri = film.imageUri)
                Country(country = film.country)
                Genres(genres = film.genres)
                Directors(directors = film.directors)
                Budget(budget = film.budget)
                Description(description = film.description)
            }
        }

    }
}

@Composable
private fun FilmPreviewImage(imageUri: Uri) {
    AsyncImage(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp)),
        model = imageUri,
        contentDescription = null
    )
}

@Composable
private fun Country(country: String?) {
    Text(
        modifier = Modifier
            .padding(20.dp),
        text = "Страна: ${country ?: "не указана"}",
        fontSize = 20.sp,
        style = Typography.titleLarge,
        fontStyle = FontStyle.Italic
    )
}

@Composable
private fun Genres(genres: String) {
    Text(
        modifier = Modifier
            .padding(20.dp),
        text = "Жанр: $genres",
        fontSize = 20.sp,
        style = Typography.titleLarge,
        fontStyle = FontStyle.Italic
    )
}

@Composable
private fun Directors(directors: String) {
    Text(
        modifier = Modifier
            .padding(20.dp),
        text = "Режисёры: $directors",
        fontSize = 20.sp,
        style = Typography.titleLarge,
        fontStyle = FontStyle.Italic
    )
}

@Composable
private fun Budget(budget: String) {
    Text(
        modifier = Modifier
            .padding(20.dp),
        text = "Бюджет: ${budget}",
        fontSize = 20.sp,
        style = Typography.titleLarge,
        fontStyle = FontStyle.Italic
    )
}

@Composable
private fun Description(description: String?) {
    Text(
        modifier = Modifier
            .padding(8.dp),
        text = "Описание: ${description ?: "не указано"}",
        fontSize = 16.sp
    )
}

@Preview(showBackground = true)
@Composable
fun FilmDetailScreenPreview() {
    FilmDetailsScreen(
        filmDetailScreenHandler = FilmDetailScreenHandlerImpl(
            rememberNavController()
        ),
        film = FilmItem(
            name = "",
            genres = "",
            directors = "",
            budget = "",
            country = "",
            imageUri = Uri.parse(""),
            description = "",
        )
    )
}