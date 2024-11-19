package com.example.android_films_app.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.outlined.Keyboard
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.android_films_app.presentation.handler.EditProfileScreenHandler
import com.example.android_films_app.presentation.handler.EditProfileScreenHandlerImpl
import com.example.android_films_app.presentation.screen.model.ScreenBar
import com.example.android_films_app.presentation.viewModel.EditProfileScreenViewModel

/**
 * @author Lapoushko
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    viewModel: EditProfileScreenViewModel = hiltViewModel(),
    handler: EditProfileScreenHandler
) {
//    val _isClickPicture = viewModel._isClickPicture
    val viewState = viewModel.viewState
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    IconButton(onClick = { handler.onToBack() }) {
                        Icon(imageVector = Icons.Filled.ArrowBackIosNew, contentDescription = null)
                    }
                },
                title = {
                    Text(
                        text = ScreenBar.Films.title ?: "Нет названия"
                    )
                },
                actions = {
                    IconButton(onClick = {
                        handler.onToBack()
                        viewModel.save()
                    }) {
                        Icon(imageVector = Icons.Filled.Save, contentDescription = null)
                    }
                }
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            TextFieldOption(
                text = viewState.name,
                onTextChange = {
                    viewModel.updateUsername(it)
                },
                label = "ФИО"
            )

            TextFieldOption(
                text =  viewState.description,
                onTextChange = {
                    viewModel.updateDescription(it)
                },
                label = "Описание"
            )

            TextFieldOption(
                text = viewState.resumeUrl.toString(),
                onTextChange = {
                    viewModel.updateResumeUrl(it)
                },
                label = "Ссылка на pdf"
            )
        }
    }
}

@Composable
fun TextFieldOption(
    text: String,
    onTextChange: (String) -> Unit,
    imageVector: ImageVector = Icons.Outlined.Keyboard,
    keyboardType: KeyboardType = KeyboardType.Text,
    label: String,
) {
    TextField(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 20.dp, vertical = 10.dp)
        .clip(RoundedCornerShape(20.dp)),
        value = text,
        onValueChange = {
            onTextChange(it)
        },
        placeholder = {
            Text(
                text = label, fontSize = 15.sp
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        leadingIcon = {
            Icon(
                imageVector = imageVector, contentDescription = null
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun EditProfileScreenPreview() {
    EditProfileScreen(
        viewModel = hiltViewModel(), handler = EditProfileScreenHandlerImpl(
            rememberNavController()
        )
    )
}
