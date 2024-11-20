package com.example.android_films_app.presentation.viewModel

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_films_app.domain.usecase.storage.proto.SubscribeEditUserUseCase
import com.example.android_films_app.domain.usecase.storage.proto.SubscribeGetUserUseCase
import com.example.android_films_app.presentation.extension.toFormattedString
import com.example.android_films_app.presentation.mapper.user.UserItemToUserMapper
import com.example.android_films_app.presentation.mapper.user.UserToUserItemMapper
import com.example.android_films_app.presentation.model.UserItem
import com.example.android_films_app.presentation.state.EditProfileScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

/**
 * @author Lapoushko
 */
@HiltViewModel
class EditProfileScreenViewModel @Inject constructor(
    private val subscribeGetUserUseCase: SubscribeGetUserUseCase,
    private val subscribeEditUserUseCase: SubscribeEditUserUseCase,
    private val userToUserItemMapper: UserToUserItemMapper,
    private val userItemToUserMapper: UserItemToUserMapper,
) : ViewModel() {
    private val _viewState = MutableEditProfileState()
    val viewState = _viewState as EditProfileScreenState

    init {
        load()
        _viewState.isNeedToShowPermission = true
    }

    fun updateUsername(input: String) {
        _viewState.name = input
    }

    fun updateDescription(input: String) {
        _viewState.description = input
    }

    fun updatePhotoUrl(uri: Uri) {
        _viewState.photoUrl = uri
    }

    fun updateResumeUrl(input: String) {
        _viewState.resumeUrl = Uri.parse(input)
    }

    fun updateTimeInput(input: String){
        _viewState.timerString = input
        validateTimer()
    }

    fun updateTimeTimer(hour: Int, minute: Int){
        _viewState.timer = LocalTime.of(hour, minute)
        _viewState.timerString = _viewState.timer.format(DateTimeFormatter.ofPattern(TIME_FORMAT))
        _viewState.timerError = null
    }

    fun onClosePermission(){
        _viewState.isNeedToShowPermission = false
    }

    fun onSelectPhoto(){
        _viewState.isNeedToShowSelect = true
    }

    fun onSelectDismiss(){
        _viewState.isNeedToShowSelect = false
    }

    fun onToggleTimer(){
        _viewState.isNeedToShowTimer = !_viewState.isNeedToShowTimer
    }

    fun save(onToBack : () -> Unit) {
        validateTimer()
        if (_viewState.timerError.isNullOrEmpty()) {
            viewModelScope.launch {
                val user = UserItem(
                    name = viewState.name,
                    description = viewState.description,
                    photoUrl = viewState.photoUrl,
                    resumeUrl = viewState.resumeUrl
                )
                subscribeEditUserUseCase.edit(userItemToUserMapper.invoke(user))
                onToBack()
            }
        }
    }

    private fun load(){
        viewModelScope.launch {
            subscribeGetUserUseCase.get().collect{
                val user = userToUserItemMapper.invoke(it)
                _viewState.name = user.name
                _viewState.description = user.description
                _viewState.photoUrl = Uri.parse(user.photoUrl.toFormattedString())
                _viewState.resumeUrl = Uri.parse(user.resumeUrl.toFormattedString())
            }
        }
    }

    private fun validateTimer(){
        try {
            _viewState.timer = LocalTime.parse(_viewState.timerString, DateTimeFormatter.ofPattern(
                TIME_FORMAT))
            _viewState.timerError = null
        } catch (e: Exception){
            _viewState.timerError = e.toString()
        }
    }

    private class MutableEditProfileState : EditProfileScreenState {
        override var photoUrl: Uri by mutableStateOf(Uri.EMPTY)
        override var name by mutableStateOf("")
        override var description: String by mutableStateOf("")
        override var resumeUrl: Uri by mutableStateOf(Uri.parse("https://elibrary.ru/download/elibrary_44394445_69180276.pdf"))
        override var timerString: String by mutableStateOf("")
        override var timer: LocalTime by mutableStateOf(LocalTime.now())
        override var timerError: String? by mutableStateOf(null)
        override var isNeedToShowPermission by mutableStateOf(false)
        override var isNeedToShowSelect by mutableStateOf(false)
        override var isNeedToShowTimer: Boolean by mutableStateOf(false)
    }

    companion object{
        const val TIME_FORMAT = "HH:mm"
    }
}