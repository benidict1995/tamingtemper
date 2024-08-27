package com.benidict.tamingtemper.screen.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benidict.data.usecase.LoadActivitiesUseCase
import com.benidict.data.usecase.LogOutUseCase
import com.benidict.domain.model.CalendarUIModel
import com.benidict.domain.model.LevelDTO
import com.benidict.tamingtemper.utils.daysInWeekArray
import com.benidict.tamingtemper.utils.getDayToday
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val loadActivitiesUseCase: LoadActivitiesUseCase,
    private val logOutUseCase: LogOutUseCase
) : ViewModel() {

    private val _state: MutableSharedFlow<HomeState> = MutableSharedFlow()
    val state = _state.asSharedFlow()

    val selectedDate: MutableStateFlow<String> = MutableStateFlow(getDayToday().toString())
    val daysInWeek: MutableStateFlow<ArrayList<CalendarUIModel>> = MutableStateFlow(daysInWeekArray())

    fun logOut() {
        viewModelScope.launch {
            logOutUseCase.invoke()
            _state.emit(HomeState.LogOut)
        }
    }

    fun loadDaysInWeek() {
        viewModelScope.launch {
            daysInWeek.value.map {
                it.copy(
                    isSelected = it.date == selectedDate.value
                )
            }
        }
    }

    fun loadActivities() {
        val filteredActivities: MutableList<LevelDTO> = mutableListOf()
        viewModelScope.launch {
            val invoke = loadActivitiesUseCase.loadActivities()
            invoke.levels.forEachIndexed { _, levelDTO ->
                levelDTO.dates.filter {
                    it.date == selectedDate.value
                }.map {
                    filteredActivities.add(levelDTO)
                }
            }
            delay(500)
            _state.emit(HomeState.LoadActivities(filteredActivities))
        }
    }
}