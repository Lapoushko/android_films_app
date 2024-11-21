package com.example.android_films_app.domain.usecase.storage.proto

import com.example.android_films_app.domain.entity.User
import com.example.android_films_app.domain.repository.UserRepository
import javax.inject.Inject

/**
 * @author Lapoushko
 */
interface SubscribeEditUserUseCase {
    suspend fun edit(user: User)
}

class SubscribeEditUserUseCaseImpl @Inject constructor(private val repository: UserRepository) :
    SubscribeEditUserUseCase {
    override suspend fun edit(user: User) {
        repository.save(user)
    }
}