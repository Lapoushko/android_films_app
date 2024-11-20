package com.example.android_films_app.presentation.state

import android.net.Uri

/**
 * @author Lapoushko
 */
interface EditProfileScreenState {
    val photoUrl: Uri
    val name: String
    val resumeUrl: Uri
    val description: String
    val isNeedToShowPermission: Boolean
    val isNeedToShowSelect: Boolean
}