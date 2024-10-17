package com.example.android_films_app.di

import com.example.android_films_app.data.storage.dao.FilmsDao
import com.example.android_films_app.data.storage.dao.FilmsDaoImpl
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
object DaoModule {
    @Singleton
    @Provides
    fun provideFilmsDao(
    ): FilmsDao {
        return FilmsDaoImpl()
    }
}