package com.example.android_films_app.presentation.screen.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * @author Lapoushko
 *
 * Экраны нижнего бара
 */
sealed class ScreenBar(
    val route: String,
    val title: String? = null,
    val setIcon: ImageVector? = null,
    val unsetIcon: ImageVector? = null,
){
    /**
     * Экран фильмов
     */
    data object Films : ScreenBar(
        route = FILMS_ROUTE,
        title = FILMS_TITLE,
        setIcon = FILMS_SET_ICON,
        unsetIcon = FILMS_UNSET_ICON,
    )

    /**
     * Экран домашней страцицы
     */
    data object Home : ScreenBar(
        route = HOME_ROUTE,
        title = HOME_TITLE,
        setIcon = HOME_SET_ICON,
        unsetIcon = HOME_UNSET_ICON
    )
    /**
     * Экран лучших фильмов
     */
    data object Favourite : ScreenBar(
        route = FAVOURITE_ROUTE,
        title = FAVOURITE_TITLE,
        setIcon = FAVOURITE_SET_ICON,
        unsetIcon = FAVOURITE_UNSET_ICON
    )

    /**
     * Экран уведомлений
     */
    data object Notifications : ScreenBar(
        route = NOTIFICATIONS_ROUTE,
        title = NOTIFICATIONS_TITLE,
        setIcon = NOTIFICATIONS_SET_ICON,
        unsetIcon = NOTIFICATIONS_UNSET_ICON
    )

    companion object{
        private const val FILMS_ROUTE = "FILMS_ROUTE"
        private const val FILMS_TITLE = "Фильмы"
        private val FILMS_SET_ICON = Icons.Filled.AccountCircle
        private val FILMS_UNSET_ICON = Icons.Outlined.AccountCircle

        private const val HOME_ROUTE = "HOME_ROUTE"
        private const val HOME_TITLE = "Домашняя страница"
        private val HOME_SET_ICON = Icons.Filled.Home
        private val HOME_UNSET_ICON = Icons.Outlined.Home

        private const val FAVOURITE_ROUTE = "FAVOURITE_ROUTE"
        private const val FAVOURITE_TITLE = "Любимые фильмы"
        private val FAVOURITE_SET_ICON = Icons.Filled.Favorite
        private val FAVOURITE_UNSET_ICON = Icons.Outlined.FavoriteBorder

        private const val NOTIFICATIONS_ROUTE = "NOTIFICATIONS_ROUTE"
        private const val NOTIFICATIONS_TITLE = "Уведомления"
        private val NOTIFICATIONS_SET_ICON = Icons.Filled.Notifications
        private val NOTIFICATIONS_UNSET_ICON = Icons.Outlined.Notifications
    }
}