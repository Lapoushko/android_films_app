package com.example.android_films_app.di

import com.example.android_films_app.data.storage.repository.FilmsDataRepository
import com.example.android_films_app.domain.usecase.SubscribeAllFilmsUseCase
import com.example.android_films_app.domain.usecase.SubscribeAllFilmsUseCaseImpl
import com.example.android_films_app.domain.usecase.SubscribeFilmUseCase
import com.example.android_films_app.domain.usecase.SubscribeFilmUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author Lapoushko
 *
 * Модуль для usecases
 */
@Module
@InstallIn(SingletonComponent::class)
object UsecaseModule {
    @Singleton
    @Provides
    fun provideSubscribeAllFilmsUseCase(
        repository: FilmsDataRepository
    ) : SubscribeAllFilmsUseCase{
        return SubscribeAllFilmsUseCaseImpl(repository)
    }

    @Singleton
    @Provides
    fun provideSubscribeFilmUseCase(
        repository: FilmsDataRepository
    ) : SubscribeFilmUseCase{
        return SubscribeFilmUseCaseImpl(repository)
    }
}