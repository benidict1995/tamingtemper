package com.benidict.tamingtemper.screen.home

import com.benidict.domain.model.LevelDTO

sealed class HomeState{
    data object LogOut: HomeState()
    data class LoadActivities(val data: List<LevelDTO>): HomeState()
}