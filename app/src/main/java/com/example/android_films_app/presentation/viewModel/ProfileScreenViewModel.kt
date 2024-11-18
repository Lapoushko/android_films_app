package com.example.android_films_app.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_films_app.presentation.model.UserItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Lapoushko
 */
@HiltViewModel
class ProfileScreenViewModel @Inject constructor(): ViewModel() {
    private val _userItem = MutableStateFlow<UserItem?>(null)
    val userItem: StateFlow<UserItem?> = _userItem.asStateFlow()

    init {
        load()
    }

    fun load(){
        viewModelScope.launch {
            //TODO
        }
    }
}