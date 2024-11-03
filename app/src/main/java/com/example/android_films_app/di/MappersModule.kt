package com.example.android_films_app.di

import com.example.android_films_app.data.network.mapper.FilmResponseToDbMapper
import com.example.android_films_app.data.network.mapper.FilmResponseToDbMapperImpl
import com.example.android_films_app.data.network.mapper.FilmRetrofitToResponseMapper
import com.example.android_films_app.data.network.mapper.FilmRetrofitToResponseMapperImpl
import com.example.android_films_app.data.storage.mapper.FilmToFilmDbMapper
import com.example.android_films_app.data.storage.mapper.FilmToFilmDbMapperImpl
import com.example.android_films_app.data.storage.mapper.FilmsDbToFilmsMapper
import com.example.android_films_app.data.storage.mapper.FilmsDbToFilmsMapperImpl
import com.example.android_films_app.presentation.mapper.FilmItemToFilmMapper
import com.example.android_films_app.presentation.mapper.FilmItemToFilmMapperImpl
import com.example.android_films_app.presentation.mapper.FilmToUiItemMapper
import com.example.android_films_app.presentation.mapper.FilmToUiItemMapperImpl
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
    fun provideFilmItemToFilmMapper(): FilmItemToFilmMapper{
        return FilmItemToFilmMapperImpl()
    }

    @Singleton
    @Provides
    fun provideFilmToFilmDbMapper(): FilmToFilmDbMapper{
        return FilmToFilmDbMapperImpl()
    }
}