package com.example.android_films_app.data.network.mapper

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
            country = filmRetrofit.countries?.map { it.name ?: "" },
            directors = filmRetrofit.persons?.filter { it.profession == "режиссеры" }
                ?.map { it.name.toString() },
            budget = filmRetrofit.budget?.value,
            genres = filmRetrofit.genres?.map { it.name.toString() },
            description = filmRetrofit.description,
            year = filmRetrofit.year,
            imageUri = filmRetrofit.poster?.previewUrl ?: ""
        )
    }
}