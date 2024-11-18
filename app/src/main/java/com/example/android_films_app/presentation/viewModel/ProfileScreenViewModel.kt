package com.example.android_films_app.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_films_app.domain.usecase.storage.proto.SubscribeGetUserUseCase
import com.example.android_films_app.presentation.extension.toFormattedUri
import com.example.android_films_app.presentation.mapper.user.UserToUserItemMapper
import com.example.android_films_app.presentation.model.UserItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Lapoushko
 */
@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    private val subscribeGetUserUseCase: SubscribeGetUserUseCase,
    private val userToUserItemMapper: UserToUserItemMapper
): ViewModel() {
    private val _user = MutableStateFlow<UserItem?>(UserItem("","","".toFormattedUri(),"".toFormattedUri()))
    val user = _user.asStateFlow()

    init {
        load()
    }

    private fun load() {
        viewModelScope.launch {
            subscribeGetUserUseCase.get().collect {
                _user.value = userToUserItemMapper.invoke(it)
            }
        }
    }
}