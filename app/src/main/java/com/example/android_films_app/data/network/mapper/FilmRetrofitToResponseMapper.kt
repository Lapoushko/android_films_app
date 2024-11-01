package com.example.android_films_app.data.network.mapper

import android.net.Uri
import com.example.android_films_app.data.network.entity.FilmResponse
import com.example.android_films_app.data.network.entity.retrofit.FilmRetrofit
import javax.inject.Inject

/**
 * @author Lapoushko
 */
interface FilmRetrofitToResponseMapper {
    operator fun invoke(filmRetrofit: FilmRetrofit): FilmResponse
}

class FilmRetrofitToResponseMapperImpl @Inject constructor() : FilmRetrofitToResponseMapper {
    override fun invoke(filmRetrofit: FilmRetrofit): FilmResponse {
        return FilmResponse(
            id = filmRetrofit.id,
            name = filmRetrofit.name,
            country = filmRetrofit.countries?.joinToString { it.name.toString() },
            directors = filmRetrofit.persons?.filter { it.profession == "режиссеры" }
                ?.map { it.name.toString() },
            budget = filmRetrofit.budget?.value,
            genres = filmRetrofit.genres?.map { it.name.toString() },
            description = filmRetrofit.description,
            imageUri = filmRetrofit.poster?.previewUrl?.let { Uri.parse(it) } ?: Uri.EMPTY
        )
    }
}