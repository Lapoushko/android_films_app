package com.example.android_films_app.presentation.model

import android.net.Uri

/**
 * @author Lapoushko
 */
data class UserItem(
    val name: String,
    val description: String,
    val photoUrl: Uri,
    val resumeUrl: Uri
)