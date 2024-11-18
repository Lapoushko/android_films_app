package com.example.android_films_app.presentation.screen

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.android_films_app.presentation.handler.ProfileScreenHandler
import com.example.android_films_app.presentation.handler.ProfileScreenHandlerImpl
import com.example.android_films_app.presentation.model.UserItem
import com.example.android_films_app.presentation.screen.model.ScreenBar

/**
 * @author Lapoushko
 *
 * экран профиля
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    profileScreenHandler: ProfileScreenHandler,
) {
    val userItem = UserItem(name = "имя", description = "описание", photoUrl = Uri.EMPTY, resumeUri = Uri.EMPTY)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(title = {
                    Text(
                        text = ScreenBar.Films.title ?: "Нет названия"
                    )
                })
            },
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ProfilePictureButton(userItem.photoUrl)

                Spacer(modifier = Modifier.height(16.dp))

                ProfileInfoSection(userItem)

                Spacer(modifier = Modifier.height(24.dp))

                ResumeButton()
            }
        }
    }
}

@Composable
fun ProfilePictureButton(photoUrl: Uri?) {
    Box(
        modifier = Modifier
            .size(120.dp)
            .clip(CircleShape)
            .background(color = MaterialTheme.colorScheme.surfaceVariant),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = photoUrl,
            contentDescription = "Profile Picture",
            modifier = Modifier.size(100.dp),
            contentScale = ContentScale.Crop
        )

        IconButton(onClick = { /* Handle picture change */ }) {
            Icon(
                imageVector = Icons.Filled.Edit,
                contentDescription = "Edit Profile Picture",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun ProfileInfoSection(userItem: UserItem) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = userItem.name,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = userItem.description,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
fun ResumeButton() {
    Button(
        onClick = { /* Handle resume button click */ },
        modifier = Modifier.width(200.dp),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Text(
            text = "Resume",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(profileScreenHandler = ProfileScreenHandlerImpl(rememberNavController()))
}