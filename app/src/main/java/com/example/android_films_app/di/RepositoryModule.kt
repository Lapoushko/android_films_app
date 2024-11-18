package com.example.android_films_app.di

import com.example.android_films_app.data.network.repository.FilmsRepositoryImpl
import com.example.android_films_app.data.storage.repository.PreferencesRepositoryImpl
import com.example.android_films_app.data.storage.repository.UserRepositoryImpl
import com.example.android_films_app.domain.repository.FilmsRepository
import com.example.android_films_app.domain.repository.PreferencesRepository
import com.example.android_films_app.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * @author Lapoushko
 *
 * Модуль для репозиториев
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindFilmsRepository(
        impl: FilmsRepositoryImpl
    ): FilmsRepository

    @Binds
    abstract fun bindPreferencesRepository(
        impl: PreferencesRepositoryImpl
    ) : PreferencesRepository

    @Binds
    abstract fun bindUserRepository(
        impl: UserRepositoryImpl
    ) : UserRepository
}