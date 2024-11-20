package com.example.android_films_app.data.storage.mapper.user

import com.example.android_films_app.data.storage.entity.UserData
import com.example.android_films_app.domain.entity.User
import javax.inject.Inject

/**
 * @author Lapoushko
 * перевод user domain в data
 */
interface UserToUserDataMapper {
    operator fun invoke(user: User): UserData
}

class UserToUserDataMapperImpl @Inject constructor() : UserToUserDataMapper{
    override fun invoke(user: User): UserData {
        return UserData(
            name = user.name,
            description = user.description,
            photo = user.photo,
            resume = user.resume,
        )
    }
}