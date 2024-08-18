package com.benidict.tamingtemper.screen.signin

sealed class SignInState {
    data class ShowError(val msg: String): SignInState()
    data object NavigateToHome: SignInState()
}