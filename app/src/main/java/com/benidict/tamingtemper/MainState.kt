package com.benidict.tamingtemper

sealed class MainState {
    data class SetUpNavigation(val isLoggedIn: Boolean): MainState()
}