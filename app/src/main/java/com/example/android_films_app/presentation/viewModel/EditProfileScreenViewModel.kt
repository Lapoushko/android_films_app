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
import com.example.android_films_app.presentation.state.EditProfileScreenState
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
    private val _viewState = MutableEditProfileState()
    val viewState = _viewState as EditProfileScreenState

    fun updateUsername(input: String) {
        _viewState.name = input
    }

    fun updateDescription(input: String) {
        _viewState.description = input
    }

    fun updatePhotoUrl(input: String) {
        _viewState.photoUrl = input.toFormattedUri()
    }

    fun updateResumeUrl(input: String) {
        _viewState.resumeUrl = input.toFormattedUri()
    }

    fun save() {
        viewModelScope.launch {
            val user = UserItem(
                name = viewState.name,
                description = viewState.description,
                photoUrl = viewState.photoUrl,
                resumeUrl = viewState.resumeUrl
            )
            subscribeEditUserUseCase.edit(userItemToUserMapper.invoke(user))
        }
    }

    private class MutableEditProfileState : EditProfileScreenState {
        override var photoUrl: Uri by mutableStateOf(Uri.EMPTY)
        override var name by mutableStateOf("")
        override var description: String by mutableStateOf("")
        override var resumeUrl: Uri by mutableStateOf(Uri.EMPTY)
        override var isNeedToShowPermission by mutableStateOf(false)
        override var isNeedToShowSelect: Boolean by mutableStateOf(false)
    }
}