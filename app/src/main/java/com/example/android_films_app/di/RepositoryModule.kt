package com.example.android_films_app.di

import com.example.android_films_app.data.storage.dao.FilmsDao
import com.example.android_films_app.data.storage.mapper.FilmsDbToFilmsMapper
import com.example.android_films_app.data.storage.repository.FilmsDataRepository
import com.example.android_films_app.data.storage.repository.FilmsDataRepositoryImpl
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
object RepositoryModule {
    @Singleton
    @Provides
    fun provideFilmsDataRepository(
        filmsDao: FilmsDao,
        filmDbToFilmMapper: FilmsDbToFilmsMapper
    ): FilmsDataRepository {
        return FilmsDataRepositoryImpl(filmsDao = filmsDao, filmDbToFilmMapper = filmDbToFilmMapper)
    }
}