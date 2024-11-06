package com.example.android_films_app.presentation.navigation.ui

import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.android_films_app.presentation.extension.AddItem
import com.example.android_films_app.presentation.screen.model.ScreenBar
import com.example.android_films_app.presentation.viewModel.BottomBarScreenViewModel

/**
 * @author Lapoushko
 *
 * Нижний бар
 * @param navController контроллер навигации
 */
@Composable
fun BottomBar(
    navController: NavHostController,
    viewModel: BottomBarScreenViewModel?
) {
    val items = listOf(
        ScreenBar.Home,
        ScreenBar.Films,
        ScreenBar.Favourite,
        ScreenBar.Notifications
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val destination = navBackStackEntry?.destination

    val countQueries by viewModel?.countQueries?.collectAsState(initial = 0)
        ?: remember { mutableIntStateOf(0) }

    NavigationBar {
        items.forEach { screen ->
            AddItem(
                screen = screen,
                destination = destination,
                navController = navController,
                badges = if (screen == ScreenBar.Films || screen == ScreenBar.Favourite) countQueries else 0
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomBarPreview() {
    BottomBar(
        navController = rememberNavController(),
        null
    )
}