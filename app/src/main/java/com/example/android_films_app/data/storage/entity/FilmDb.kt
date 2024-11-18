package com.example.android_films_app.data.storage.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android_films_app.data.storage.util.ConstantsDatabase

/**
 * @author Lapoushko
 *
 * Фильм
 * @param id айди фильма
 * @param countries страна создания
 * @param directors режисёры
 * @param budget бюджет фильма
 * @param name имя фильма
 * @param genres жанры фильмов
 * @param description описание фильма
 * @param imageUri путь до изображения фильма
 */
@Entity(tableName = ConstantsDatabase.NAME_TABLE_GROUPS)
data class FilmDb(
    @PrimaryKey(autoGenerate = true) val id: Long?,
    @ColumnInfo("name") val name: String?,
    @ColumnInfo("countries") val countries: List<String>?,
    @ColumnInfo("directors") val directors: List<String>?,
    @ColumnInfo("budget") val budget: Long?,
    @ColumnInfo("genres") val genres: List<String>?,
    @ColumnInfo("description") val description: String?,
    @ColumnInfo("year") val year: Int?,
    @ColumnInfo("imageUri") val imageUri: String?,
    @ColumnInfo("isFavourite") val isFavourite: Boolean? = false
)