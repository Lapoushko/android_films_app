package com.example.android_films_app.di

import com.example.android_films_app.data.storage.mapper.FilmsDbToFilmsMapper
import com.example.android_films_app.data.storage.mapper.FilmsDbToFilmsMapperImpl
import com.example.android_films_app.presentation.mapper.FilmToUiItemMapper
import com.example.android_films_app.presentation.mapper.FilmToUiItemMapperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author Lapoushko
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
    fun provideFilmToUiItemMapper() : FilmToUiItemMapper{
        return FilmToUiItemMapperImpl()
    }
}