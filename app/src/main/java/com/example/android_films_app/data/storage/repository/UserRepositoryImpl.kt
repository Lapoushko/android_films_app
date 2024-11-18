package com.example.android_films_app.data.storage.repository

import com.example.android_films_app.domain.entity.User
import com.example.android_films_app.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @author Lapoushko
 * репозиторий пользователя
 */
class UserRepositoryImpl @Inject constructor() : UserRepository{
    /**
     * сохранить информацию
     */
    override suspend fun save(user: User) {
        TODO("Not yet implemented")
    }

    override fun getUser(): Flow<User> {
        TODO("Not yet implemented")
    }
}