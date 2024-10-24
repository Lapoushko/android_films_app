package com.example.android_films_app.presentation.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.android_films_app.presentation.handler.FavouritesScreenHandler
import com.example.android_films_app.presentation.handler.FavouritesScreenHandlerImpl
import com.example.android_films_app.presentation.screen.model.ScreenBar

/**
 * @author Lapoushko
 *
 * Лучшие фильмы
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouritesScreen(
    favouritesScreenHandler: FavouritesScreenHandler
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(title = {
                    Text(
                        text = ScreenBar.Favourite.title ?: "Нет названия"
                    )
                })
            }
        ) { innerPadding ->
            Text(
                modifier = Modifier.padding(innerPadding),
                text = "Hello Favourites")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FavouritesScreenPreview() {
    FavouritesScreen(favouritesScreenHandler = FavouritesScreenHandlerImpl(rememberNavController()))
}