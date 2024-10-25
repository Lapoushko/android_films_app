package com.example.android_films_app.di

import com.example.android_films_app.data.network.repository.FilmsRepositoryImpl
import com.example.android_films_app.domain.repository.FilmsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * @author Lapoushko
 *
 * Модуль для репозитория
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindFilmsRepository(
        impl: FilmsRepositoryImpl
    ): FilmsRepository
}