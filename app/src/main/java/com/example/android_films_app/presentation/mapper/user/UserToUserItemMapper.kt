package com.example.android_films_app.presentation.mapper.user

import android.net.Uri
import com.example.android_films_app.domain.entity.User
import com.example.android_films_app.presentation.extension.toFormattedUri
import com.example.android_films_app.presentation.model.UserItem
import javax.inject.Inject

/**
 * @author Lapoushko
 * перевод из domain в UI
 */
interface UserToUserItemMapper{
    fun invoke(user: User) : UserItem
}

class UserToUserItemMapperImpl @Inject constructor() : UserToUserItemMapper{
    override fun invoke(user: User): UserItem {
        return UserItem(
            name = user.name.ifEmpty { "Нет имени" },
            description = user.description.ifEmpty { "Нет имени" },
            photoUrl = if(user.photo.isEmpty()) Uri.EMPTY else user.photo.toFormattedUri(),
            resumeUrl = if(user.resume.isEmpty()) Uri.EMPTY else user.resume.toFormattedUri()
        )
    }
}