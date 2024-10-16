package com.example.android_films_app.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.android_films_app.presentation.model.FilmItem

/**
 * @author Lapoushko
 */

@Composable
fun FilmDetailsScreen(film: FilmItem){

}

@Preview(showBackground = true)
@Composable
fun FilmDetailScreenPreview(){
    FilmDetailsScreen(film = FilmItem("",""))
}