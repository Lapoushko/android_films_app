package com.example.android_films_app.di

import android.content.Context
import androidx.room.Room
import com.example.android_films_app.data.storage.dao.FilmsDao
import com.example.android_films_app.data.storage.dao.FilmsDatabase
import com.example.android_films_app.data.storage.util.ConstantsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author Lapoushko
 * Модуль для DAO
 */
@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Singleton
    @Provides
    fun provideFilmsDatabase(@ApplicationContext context: Context) : FilmsDatabase =
        Room.databaseBuilder(
            context,
            FilmsDatabase::class.java,
            ConstantsDatabase.NAME_DATABASE
        )
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideFilmsDao(appDatabase: FilmsDatabase): FilmsDao{
        return appDatabase.FilmsDao()
    }
}