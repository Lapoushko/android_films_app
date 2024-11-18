package com.example.android_films_app.data.storage.repository

import android.content.Context
import androidx.datastore.dataStore
import com.example.android_films_app.data.storage.mapper.user.UserDataToUserMapper
import com.example.android_films_app.data.storage.util.ConstantsProto
import com.example.android_films_app.data.storage.util.ProtoDataSerializer
import com.example.android_films_app.domain.entity.User
import com.example.android_films_app.domain.repository.UserRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * @author Lapoushko
 * репозиторий пользователя
 */
class UserRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val userDataToUserMapper: UserDataToUserMapper,
) : UserRepository {
    /**
     * сохранить информацию
     */
    override suspend fun save(user: User) {
        context.protoDataStore.updateData { data ->
            data.copy(
                name = user.name,
                description = user.description,
                photo = user.photo,
                resume = user.resume
            )
        }
    }

    override fun getUser(): Flow<User> {
        return context.protoDataStore.data.map { data ->
            userDataToUserMapper.invoke(data)
        }
    }
}

private val Context.protoDataStore by dataStore(ConstantsProto.FILE_NAME_PROTO, ProtoDataSerializer)