package com.example.android_films_app.presentation.state

import android.net.Uri

/**
 * @author Lapoushko
 */
interface ProfileScreenState{
    val name: String
    val description: String
    val photoUrl: Uri
    val resumeUrl: Uri
}