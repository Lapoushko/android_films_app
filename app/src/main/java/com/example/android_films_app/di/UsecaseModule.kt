package com.example.android_films_app.di

import com.example.android_films_app.domain.repository.FilmsRepository
import com.example.android_films_app.domain.usecase.network.SubscribeAllFilmsUseCase
import com.example.android_films_app.domain.usecase.network.SubscribeAllFilmsUseCaseImpl
import com.example.android_films_app.domain.usecase.network.SubscribeCheckInternetUseCase
import com.example.android_films_app.domain.usecase.network.SubscribeCheckInternetUseCaseImpl
import com.example.android_films_app.domain.usecase.storage.SubscribeFavouriteFilm
import com.example.android_films_app.domain.usecase.storage.SubscribeFavouriteFilmImpl
import com.example.android_films_app.domain.usecase.storage.SubscribeGetFavouriteFilmUseCase
import com.example.android_films_app.domain.usecase.storage.SubscribeGetFavouriteFilmUseCaseImpl
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
    ): SubscribeAllFilmsUseCase {
        return SubscribeAllFilmsUseCaseImpl(repository)
    }

    @Singleton
    @Provides
    fun provideSubscribeCheckInternetUseCase(
        repository: FilmsRepository
    ): SubscribeCheckInternetUseCase {
        return SubscribeCheckInternetUseCaseImpl(repository)
    }
//    @Singleton
//    @Provides
//    fun provideSubscribeFilmUseCase(
//        repository: FilmsDataRepository
//    ) : SubscribeFilmUseCase{
//        return SubscribeFilmUseCaseImpl(repository)
//    }

    @Singleton
    @Provides
    fun provideSubscribeFavouriteFilm(repository: FilmsRepository): SubscribeFavouriteFilm {
        return SubscribeFavouriteFilmImpl(repository = repository)
    }

    @Singleton
    @Provides
    fun provideSubscribeGetFavouriteFilmUseCase(repository: FilmsRepository):
            SubscribeGetFavouriteFilmUseCase {
        return SubscribeGetFavouriteFilmUseCaseImpl(repository)
    }
}