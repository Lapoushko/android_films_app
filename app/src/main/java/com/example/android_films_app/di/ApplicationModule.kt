package com.example.android_films_app.di

import com.example.android_films_app.data.network.service.FilmService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

/**
 * @author Lapoushko
 */
@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client: OkHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .build()

        val contentType = "application/json".toMediaType()
        return Retrofit
            .Builder()
            .client(client)
            .addConverterFactory(Json {
                isLenient = true
                ignoreUnknownKeys = true
            }.asConverterFactory(contentType))
            .baseUrl(BASE_URL)
            .build()
    }

    @Provides
    fun provideFilmService(retrofit: Retrofit): FilmService {
        return retrofit.create(FilmService::class.java)
    }

    private const val BASE_URL = "https://api.kinopoisk.dev/"
}
