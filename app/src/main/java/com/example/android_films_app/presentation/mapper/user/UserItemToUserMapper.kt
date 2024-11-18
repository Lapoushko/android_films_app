package com.example.android_films_app.presentation.mapper.user

import com.example.android_films_app.domain.entity.User
import com.example.android_films_app.presentation.extension.toFormattedString
import com.example.android_films_app.presentation.model.UserItem
import javax.inject.Inject

/**
 * @author Lapoushko
 * маппер для перевода useritem в domain
 */
interface UserItemToUserMapper{
    /**
     * @param userItem айтем
     * @return в domain модель
     */
    fun invoke(userItem: UserItem) : User
}

class UserItemToUserMapperImpl @Inject constructor() : UserItemToUserMapper{
    /**
     * @param userItem айтем
     * @return в domain модель
     */
    override fun invoke(userItem: UserItem): User {
        return User(
            name = userItem.name,
            description = userItem.description,
            photo = userItem.photoUrl.toFormattedString(),
            resume = userItem.resumeUrl.toFormattedString()
        )
    }
}