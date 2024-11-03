package com.example.android_films_app.presentation.screen

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
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
                                imageVector = Icons.Filled.ArrowBackIosNew,
                                contentDescription = "Back"
                            )
                        }
                    },
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .verticalScroll(rememberScrollState())
                    .padding(innerPadding)
            ) {
                FilmHeader(film = film)
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
private fun FilmHeader(film: FilmItem) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp)
    ) {
        AsyncImage(
            model = film.imageUri,
            contentDescription = "Film Poster",
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    Brush.linearGradient(
                        colors = listOf(Color.Black.copy(alpha = 0f), Color.Black.copy(alpha = 0.6f)),
                        start = Offset(0f, 0f),
                        end = Offset(0f, Float.POSITIVE_INFINITY)
                    )
                )
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 16.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = film.name,
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                style = TextStyle(shadow = Shadow(color = Color.Black, blurRadius = 5f))
            )

            Text(
                text = buildAnnotatedString {
                    append("Год выпуска: ${film.year}")
                },
                color = Color.White,
                fontSize = 18.sp
            )
        }
    }
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
            year = "",
            description = "",
        )
    )
}