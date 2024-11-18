package com.example.android_films_app.presentation.viewModel

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_films_app.domain.usecase.storage.proto.SubscribeEditUserUseCase
import com.example.android_films_app.presentation.extension.toFormattedUri
import com.example.android_films_app.presentation.mapper.user.UserItemToUserMapper
import com.example.android_films_app.presentation.model.UserItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Lapoushko
 */
@HiltViewModel
class EditProfileScreenViewModel @Inject constructor(
    private val subscribeEditUserUseCase: SubscribeEditUserUseCase,
    private val userItemToUserMapper: UserItemToUserMapper,
) : ViewModel() {
    var _name by mutableStateOf("")
        private set
    fun updateUsername(input: String) {
        _name = input
    }

    var _description by mutableStateOf("")
        private set
    fun updateDescription(input: String) {
        _description = input
    }

    var _photoUrl by mutableStateOf(Uri.EMPTY)
        private set
    fun updatePhotoUrl(input: String) {
        _photoUrl = input.toFormattedUri()
    }

    var _resumeUrl by mutableStateOf(Uri.EMPTY)
        private set
    fun updateResumeUrl(input: String) {
        _resumeUrl = input.toFormattedUri()
    }

    fun save() {
        viewModelScope.launch {
            val user = UserItem(
                name = _name,
                description = _description,
                photoUrl = _photoUrl,
                resumeUrl = _resumeUrl
            )
            subscribeEditUserUseCase.edit(userItemToUserMapper.invoke(user))
        }
    }
}