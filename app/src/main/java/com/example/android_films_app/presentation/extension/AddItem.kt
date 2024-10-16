package com.example.android_films_app.presentation.extension

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.android_films_app.presentation.screen.Screen
import com.example.android_films_app.presentation.theme.Typography

/**
 * @author Lapoushko
 *
 * Экстеншен функция для бара навигации
 * @param screen скрин
 * @param destination куда попасть
 * @param navController контроллер навигации
 */
@Composable
fun RowScope.AddItem(
    screen: Screen,
    destination: NavDestination?,
    navController: NavHostController
) {
    NavigationBarItem(
        selected = destination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },
        icon = {
            Icon(
                imageVector = if (destination?.hierarchy?.any {
                        it.route == screen.route
                    } == true
                ) {
                    screen.setIcon
                } else {
                    screen.unsetIcon
                } ?: Icons.TwoTone.Info,
                contentDescription = screen.title
            )
        },
        label = {
            Text(
                text = screen.title ?: "Неизвестно",
                style = Typography.bodySmall,
            )
        },
        alwaysShowLabel = false
    )
}