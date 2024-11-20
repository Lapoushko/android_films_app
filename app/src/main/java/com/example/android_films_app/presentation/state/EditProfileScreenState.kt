package com.example.android_films_app.presentation.state

import android.net.Uri
import java.time.LocalTime

/**
 * @author Lapoushko
 */
interface EditProfileScreenState {
    val photoUrl: Uri
    val name: String
    val resumeUrl: Uri
    val description: String
    val timerString: String
    val timer: LocalTime
    val timerError: String?
    val isNeedToShowPermission: Boolean
    val isNeedToShowSelect: Boolean
    val isNeedToShowTimer: Boolean
}