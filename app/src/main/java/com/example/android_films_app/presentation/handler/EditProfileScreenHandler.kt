package com.example.android_films_app.presentation.handler

import androidx.navigation.NavHostController
import com.example.android_films_app.presentation.extension.canGoBack

/**
 * @author Lapoushko
 */
interface EditProfileScreenHandler{
    fun onToBack()
}

class EditProfileScreenHandlerImpl(private val navController: NavHostController) : EditProfileScreenHandler {
    override fun onToBack(){
        if (navController.canGoBack) {
            navController.popBackStack()
        }
    }
}