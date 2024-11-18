package com.example.android_films_app.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_films_app.domain.usecase.storage.preference.SubscribeGetQueriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Lapoushko
 * вью модель для нижнего бара
 */
@HiltViewModel
class BottomBarScreenViewModel @Inject constructor(
    private val subscribeGetQueriesUseCase: SubscribeGetQueriesUseCase
) : ViewModel() {

    private val _countQueries: MutableStateFlow<Int> = MutableStateFlow(0)
    val countQueries: StateFlow<Int> = _countQueries.asStateFlow()

    init {
        load()
    }

    fun load(){
        viewModelScope.launch {
            subscribeGetQueriesUseCase.getQueries().collect{
                _countQueries.value = it.size
            }
        }
    }
}