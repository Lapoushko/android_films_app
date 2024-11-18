package com.example.android_films_app.data.storage.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.android_films_app.data.storage.entity.FilmDb
import com.example.android_films_app.data.storage.util.ConstantsDatabase
import com.example.android_films_app.data.storage.util.CustomTypeConverters

/**
 * @author Lapoushko
 */
@Database(
    entities = [
        FilmDb::class
    ],
    version = ConstantsDatabase.VERSION_DATABASE
)
@TypeConverters(CustomTypeConverters::class)
abstract class FilmsDatabase: RoomDatabase(){
    abstract fun FilmsDao(): FilmsDao
}

