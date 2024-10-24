package com.example.android_films_app.presentation.extension

import android.net.Uri

/**
 * @author Lapoushko
 *
 * Экстеншен функция для кодирования и декодирования Uri
 */

fun String.toFormattedUri(oldChar: Char = '/', newChar: Char = '\\'): Uri {
    return Uri.parse(this.replace(oldChar, newChar))
}