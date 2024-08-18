package com.benidict.tamingtemper.screen.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benidict.data.usecase.LoadActivitiesUseCase
import com.benidict.domain.model.LevelDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val loadActivitiesUseCase: LoadActivitiesUseCase
): ViewModel() {

    private val _state: MutableSharedFlow<HomeState> = MutableSharedFlow()
    val state = _state.asSharedFlow()

    fun loadActivities() {
        viewModelScope.launch {
            val invoke = loadActivitiesUseCase.loadActivities()
            _state.emit(HomeState.LoadActivities(invoke.levels))

            Log.d("makerChecker", "invoke.levels:${invoke.levels}")
        }
    }
}