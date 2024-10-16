package com.example.android_films_app.presentation.handler

import android.util.Log
import androidx.navigation.NavHostController

/**
 * @author Lapoushko
 */
class FilmsScreenHandler(val navController: NavHostController) : AbstractScreenHandler() {
    fun onToFilmDetail(name: String){
        Log.d("Pi diddy",name)
//        navController.navigate(Screen.FilmDetails.createRoute(name))
    }
}