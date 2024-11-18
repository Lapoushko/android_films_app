package com.example.android_films_app.presentation.handler

import androidx.navigation.NavHostController
import com.example.android_films_app.presentation.screen.model.ScreenUser

/**
 * @author Lapoushko
 * функции экрана уведомлений
 */
interface ProfileScreenHandler {
    fun onToEdit()
}

/**
 * Реализация функций уведомлений
 * @param navController контроллер навигации
 */
class ProfileScreenHandlerImpl(private val navController: NavHostController) :
    ProfileScreenHandler {
    override fun onToEdit() {
        navController.navigate(ScreenUser.Screen.route)
    }
}