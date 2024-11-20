package com.example.android_films_app.data.storage.util

import android.util.Log
import androidx.datastore.core.Serializer
import com.example.android_films_app.data.storage.entity.UserData
import com.example.android_films_app.util.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

/**
 * @author Lapoushko
 */
object ProtoDataSerializer : Serializer<UserData> {
    override val defaultValue: UserData
        get() = UserData()

    override suspend fun readFrom(input: InputStream): UserData {
        return try {
            Json.decodeFromString(
                deserializer = UserData.serializer(),
                string = input.readBytes().decodeToString()
            )
        } catch (e: SerializationException){
            Log.e(Constants.LOG_KEY, e.toString())
            UserData()
        }
    }

    override suspend fun writeTo(t: UserData, output: OutputStream) {
        withContext(Dispatchers.IO) {
            output.write(
                Json.encodeToString(
                    serializer = UserData.serializer(),
                    value = t
                ).encodeToByteArray()
            )
        }
    }
}