package com.example.android_films_app.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.android_films_app.FilmsApplication
import com.example.android_films_app.presentation.theme.Android_films_appTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * Основная активити
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Android_films_appTheme {
                FilmsApplication()
                    .FilmsNavHost()
            }
        }
    }
}