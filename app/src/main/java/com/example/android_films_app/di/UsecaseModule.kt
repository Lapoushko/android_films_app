package com.example.android_films_app.di

import com.example.android_films_app.domain.repository.FilmsRepository
import com.example.android_films_app.domain.usecase.SubscribeAllFilmsUseCase
import com.example.android_films_app.domain.usecase.SubscribeAllFilmsUseCaseImpl
import com.example.android_films_app.domain.usecase.SubscribeCheckInternetUseCase
import com.example.android_films_app.domain.usecase.SubscribeCheckInternetUseCaseImpl
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
        repository: FilmsRepository
    ) : SubscribeAllFilmsUseCase{
        return SubscribeAllFilmsUseCaseImpl(repository)
    }

    @Singleton
    @Provides
    fun provideSubscribeCheckInternetUseCase(
        repository: FilmsRepository
    ) : SubscribeCheckInternetUseCase{
        return SubscribeCheckInternetUseCaseImpl(repository)
    }
//    @Singleton
//    @Provides
//    fun provideSubscribeFilmUseCase(
//        repository: FilmsDataRepository
//    ) : SubscribeFilmUseCase{
//        return SubscribeFilmUseCaseImpl(repository)
//    }
}