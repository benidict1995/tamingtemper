package com.benidict.tamingtemper.screen.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benidict.data.usecase.LoadActivitiesUseCase
import com.benidict.data.usecase.LogOutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val loadActivitiesUseCase: LoadActivitiesUseCase,
    private val logOutUseCase: LogOutUseCase
) : ViewModel() {

    private val _state: MutableSharedFlow<HomeState> = MutableSharedFlow()
    val state = _state.asSharedFlow()

    fun logOut() {
        viewModelScope.launch {
            logOutUseCase.invoke()
            Log.d("makerChecker", "func - logout")
            _state.emit(HomeState.LogOut)
        }
    }

    fun loadActivities() {
        viewModelScope.launch {
            val invoke = loadActivitiesUseCase.loadActivities()
            _state.emit(HomeState.LoadActivities(invoke.levels))
        }
    }
}