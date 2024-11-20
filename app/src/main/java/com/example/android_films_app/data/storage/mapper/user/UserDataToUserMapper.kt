package com.example.android_films_app.data.storage.mapper.user

import com.example.android_films_app.data.storage.entity.UserData
import com.example.android_films_app.domain.entity.User
import javax.inject.Inject

/**
 * @author Lapoushko
 */
interface UserDataToUserMapper {
    operator fun invoke(userData: UserData) : User
}

class UserDataToUserMapperImpl @Inject constructor() : UserDataToUserMapper{
    override fun invoke(userData: UserData): User {
        return User(
            name = userData.name ?: "",
            description = userData.description ?: "",
            photo = userData.photo ?: "",
            resume = userData.resume ?: ""
        )
    }
}