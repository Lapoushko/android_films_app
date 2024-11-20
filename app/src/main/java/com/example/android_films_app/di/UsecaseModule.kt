package com.example.android_films_app.di

import com.example.android_films_app.domain.repository.FilmsRepository
import com.example.android_films_app.domain.repository.PreferencesRepository
import com.example.android_films_app.domain.repository.UserRepository
import com.example.android_films_app.domain.usecase.network.SubscribeAllFilmsUseCase
import com.example.android_films_app.domain.usecase.network.SubscribeAllFilmsUseCaseImpl
import com.example.android_films_app.domain.usecase.network.SubscribeCheckInternetUseCase
import com.example.android_films_app.domain.usecase.network.SubscribeCheckInternetUseCaseImpl
import com.example.android_films_app.domain.usecase.storage.preference.SubscribeClearQueriesUseCase
import com.example.android_films_app.domain.usecase.storage.preference.SubscribeClearQueriesUseCaseImpl
import com.example.android_films_app.domain.usecase.storage.preference.SubscribeGetQueriesUseCase
import com.example.android_films_app.domain.usecase.storage.preference.SubscribeGetQueriesUseCaseImpl
import com.example.android_films_app.domain.usecase.storage.preference.SubscribeSetQueriesUseCase
import com.example.android_films_app.domain.usecase.storage.preference.SubscribeSetQueriesUseCaseImpl
import com.example.android_films_app.domain.usecase.storage.proto.SubscribeEditUserUseCase
import com.example.android_films_app.domain.usecase.storage.proto.SubscribeEditUserUseCaseImpl
import com.example.android_films_app.domain.usecase.storage.proto.SubscribeGetUserUseCase
import com.example.android_films_app.domain.usecase.storage.proto.SubscribeGetUserUseCaseImpl
import com.example.android_films_app.domain.usecase.storage.room.SubscribeFavouriteFilm
import com.example.android_films_app.domain.usecase.storage.room.SubscribeFavouriteFilmImpl
import com.example.android_films_app.domain.usecase.storage.room.SubscribeGetFavouriteFilmUseCase
import com.example.android_films_app.domain.usecase.storage.room.SubscribeGetFavouriteFilmUseCaseImpl
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

    @Singleton
    @Provides
    fun provideSubscribeGetQueriesUseCase(repository: PreferencesRepository): SubscribeGetQueriesUseCase {
        return SubscribeGetQueriesUseCaseImpl(repository)
    }

    @Singleton
    @Provides
    fun provideSubscribeSetQueriesUseCase(repository: PreferencesRepository): SubscribeSetQueriesUseCase {
        return SubscribeSetQueriesUseCaseImpl(repository)
    }

    @Singleton
    @Provides
    fun provideSubscribeClearQueriesUseCase(repository: PreferencesRepository): SubscribeClearQueriesUseCase {
        return SubscribeClearQueriesUseCaseImpl(repository)
    }

    @Singleton
    @Provides
    fun provideSubscribeGetUserUseCase(repository: UserRepository): SubscribeGetUserUseCase {
        return SubscribeGetUserUseCaseImpl(repository = repository)
    }

    @Singleton
    @Provides
    fun provideSubscribeEditUserUseCase(repository: UserRepository): SubscribeEditUserUseCase {
        return SubscribeEditUserUseCaseImpl(repository = repository)
    }
}