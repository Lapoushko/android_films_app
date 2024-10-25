package com.example.android_films_app.data.network.mapper

import android.net.Uri
import com.example.android_films_app.data.network.entity.FilmApi
import com.example.android_films_app.data.network.entity.retrofit.FilmRetrofit
import javax.inject.Inject

/**
 * @author Lapoushko
 */
interface FilmRetrofitToApiMapper {
    operator fun invoke(filmRetrofit: FilmRetrofit): FilmApi
}

class FilmRetrofitToApiMapperImpl @Inject constructor() : FilmRetrofitToApiMapper {
    override fun invoke(filmRetrofit: FilmRetrofit): FilmApi {
        return FilmApi(
            id = filmRetrofit.id,
            name = filmRetrofit.name,
            country = filmRetrofit.countries?.joinToString { it.name ?: "" },
            directors = filmRetrofit.persons?.filter { it.profession == "режиссеры" }
                ?.map { it.name ?: "" },
            budget = filmRetrofit.budget?.value,
            genres = filmRetrofit.genres?.map { it.name ?: "" },
            description = filmRetrofit.description,
            imageUri = filmRetrofit.poster?.previewUrl?.let { Uri.parse(it) } ?: Uri.EMPTY
        )
    }
}