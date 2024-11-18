package com.example.android_films_app.domain.repository

import com.example.android_films_app.domain.entity.User
import kotlinx.coroutines.flow.Flow

/**
 * @author Lapoushko
 * репозиторий пользователя
 */
interface UserRepository {
    /**
     * сохранить информацию
     */
    suspend fun save(user: User)

    fun getUser() : Flow<User>
}