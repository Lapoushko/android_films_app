package com.example.android_films_app.di

import com.example.android_films_app.data.network.mapper.FilmResponseToDbMapper
import com.example.android_films_app.data.network.mapper.FilmResponseToDbMapperImpl
import com.example.android_films_app.data.network.mapper.FilmRetrofitToResponseMapper
import com.example.android_films_app.data.network.mapper.FilmRetrofitToResponseMapperImpl
import com.example.android_films_app.data.storage.mapper.film.FilmToFilmDbMapper
import com.example.android_films_app.data.storage.mapper.film.FilmToFilmDbMapperImpl
import com.example.android_films_app.data.storage.mapper.film.FilmsDbToFilmsMapper
import com.example.android_films_app.data.storage.mapper.film.FilmsDbToFilmsMapperImpl
import com.example.android_films_app.data.storage.mapper.user.UserDataToUserMapper
import com.example.android_films_app.data.storage.mapper.user.UserDataToUserMapperImpl
import com.example.android_films_app.data.storage.mapper.user.UserToUserDataMapper
import com.example.android_films_app.data.storage.mapper.user.UserToUserDataMapperImpl
import com.example.android_films_app.presentation.mapper.film.FilmItemToFilmMapper
import com.example.android_films_app.presentation.mapper.film.FilmItemToFilmMapperImpl
import com.example.android_films_app.presentation.mapper.film.FilmToUiItemMapper
import com.example.android_films_app.presentation.mapper.film.FilmToUiItemMapperImpl
import com.example.android_films_app.presentation.mapper.user.UserItemToUserMapper
import com.example.android_films_app.presentation.mapper.user.UserItemToUserMapperImpl
import com.example.android_films_app.presentation.mapper.user.UserToUserItemMapper
import com.example.android_films_app.presentation.mapper.user.UserToUserItemMapperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author Lapoushko
 *
 * Модуль для мапперов
 */
@Module
@InstallIn(SingletonComponent::class)
object MappersModule {
    @Singleton
    @Provides
    fun provideFilmsDbToFilmsMapper(
    ) : FilmsDbToFilmsMapper {
        return FilmsDbToFilmsMapperImpl()
    }

    @Singleton
    @Provides
    fun provideFilmToUiItemMapper() : FilmToUiItemMapper {
        return FilmToUiItemMapperImpl()
    }

    @Singleton
    @Provides
    fun provideFilmApiToDbMapper(): FilmResponseToDbMapper{
        return FilmResponseToDbMapperImpl()
    }

    @Singleton
    @Provides
    fun provideFilmRetrofitToApiMapper(): FilmRetrofitToResponseMapper{
        return FilmRetrofitToResponseMapperImpl()
    }

    @Singleton
    @Provides
    fun provideFilmItemToFilmMapper(): FilmItemToFilmMapper {
        return FilmItemToFilmMapperImpl()
    }

    @Singleton
    @Provides
    fun provideFilmToFilmDbMapper(): FilmToFilmDbMapper {
        return FilmToFilmDbMapperImpl()
    }

    @Singleton
    @Provides
    fun provideUserItemToUserMapper(): UserItemToUserMapper{
        return UserItemToUserMapperImpl()
    }

    @Singleton
    @Provides
    fun provideUserToUserItemMapper(): UserToUserItemMapper {
        return UserToUserItemMapperImpl()
    }

    @Singleton
    @Provides
    fun provideUserDataToUserMapper() : UserDataToUserMapper {
        return UserDataToUserMapperImpl()
    }

    @Singleton
    @Provides
    fun provideUserToUserDataMapper() : UserToUserDataMapper {
        return UserToUserDataMapperImpl()
    }
}