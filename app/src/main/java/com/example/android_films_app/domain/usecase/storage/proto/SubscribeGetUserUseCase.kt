package com.example.android_films_app.domain.usecase.storage.proto

import com.example.android_films_app.domain.entity.User
import com.example.android_films_app.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @author Lapoushko
 */

interface SubscribeGetUserUseCase {
    fun get(): Flow<User>
}

class SubscribeGetUserUseCaseImpl @Inject constructor(private val repository: UserRepository) :
    SubscribeGetUserUseCase {
    override fun get(): Flow<User> {
        return repository.getUser()
    }
}