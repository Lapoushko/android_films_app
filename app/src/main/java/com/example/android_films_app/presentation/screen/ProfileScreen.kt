package com.example.android_films_app.presentation.screen

import android.app.DownloadManager
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.android_films_app.R
import com.example.android_films_app.presentation.handler.ProfileScreenHandler
import com.example.android_films_app.presentation.handler.ProfileScreenHandlerImpl
import com.example.android_films_app.presentation.receiver.BroadcastReceiverScreen
import com.example.android_films_app.presentation.screen.model.ScreenBar
import com.example.android_films_app.presentation.viewModel.ProfileScreenViewModel

/**
 * @author Lapoushko
 *
 * экран профиля
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    profileScreenHandler: ProfileScreenHandler,
    viewModel: ProfileScreenViewModel = hiltViewModel()
) {
    val userState = viewModel.userState
    val context = LocalContext.current

    BroadcastReceiverScreen(
        systemAction = ACCESS,
        onSystemEvent = { intent ->
            if (intent?.action == ACCESS) {
                val id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1L)
                if (id != -1L) {
                    viewModel.open(context)
                }
            }
        })


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
                ProfilePictureButton(
                    photoUrl = userState.photoUrl,
                    onToEdit = {
                        profileScreenHandler.onToEdit()
                    })

                Spacer(modifier = Modifier.height(16.dp))

                ProfileInfoSection(userState.name, userState.description)

                Spacer(modifier = Modifier.height(24.dp))

                ResumeButton(onClickDownload = {
                    viewModel.download(userState.resumeUrl, context = context)
                })
            }
        }
    }
}

@Composable
fun ProfilePictureButton(
    photoUrl: Uri?,
    onToEdit: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(120.dp)
            .clip(CircleShape)
            .background(color = MaterialTheme.colorScheme.surfaceVariant),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = photoUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(CircleShape)
                .size(128.dp),
            error = painterResource(R.drawable.camera)
        )

        IconButton(onClick = { onToEdit() }) {
            Icon(
                imageVector = Icons.Filled.Edit,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun ProfileInfoSection(
    name: String,
    description: String
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = name,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = description,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
fun ResumeButton(
    onClickDownload: () -> Unit
) {
    Button(
        onClick = {
            onClickDownload()
        },
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

private const val ACCESS = "android.intent.action.DOWNLOAD_COMPLETE"

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(profileScreenHandler = ProfileScreenHandlerImpl(rememberNavController()))
}