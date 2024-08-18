package com.benidict.tamingtemper

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benidict.data.usecase.IsLoggedInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val isLoggedInUseCase: IsLoggedInUseCase
): ViewModel() {
    private val _state: MutableSharedFlow<MainState> = MutableSharedFlow()
    val state = _state.asSharedFlow()

    fun checkIfLoggedIn() {
        viewModelScope.launch {
            Log.d("makerChecker", "checkIfLoggedIn")
            val invoke = isLoggedInUseCase.invoke()
            _state.emit(MainState.SetUpNavigation(invoke?:false))
        }
    }
}