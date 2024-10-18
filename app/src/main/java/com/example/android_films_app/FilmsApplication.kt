package com.example.android_films_app

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.android_films_app.presentation.screen.BottomBarScreen
import dagger.hilt.android.HiltAndroidApp

/**
 * @author Lapoushko
 *
 * Приложение фильмов
 */
@HiltAndroidApp
class FilmsApplication : Application(){
    @Composable
    fun FilmsNavHost(){
        val navController = rememberNavController()
        BottomBarScreen(
            navController = navController
        )
    }
}