package com.example.android_films_app.presentation.extension

import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController

/**
 * @author Lapoushko
 * функция, позволяющая вернуться назад по навигации
 */

val NavHostController.canGoBack: Boolean
    get() = this.currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED