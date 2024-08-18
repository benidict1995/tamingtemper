package com.benidict.tamingtemper.screen.signin

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benidict.data.usecase.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    private val signInUseCase: SignInUseCase
) : ViewModel() {
    private val _state: MutableSharedFlow<SignInState> = MutableSharedFlow()
    val state = _state.asSharedFlow()

    val usernameState: MutableStateFlow<String> = MutableStateFlow("")
    val passwordState: MutableStateFlow<String> = MutableStateFlow("")
    var isRequirementsState: Flow<Boolean> = flowOf(false)

    fun onUsernameChanged(value: String) {
        viewModelScope.launch {
            usernameState.value = value
            validateFields()
        }
    }

    fun onPasswordChanged(value: String) {
        viewModelScope.launch {
            passwordState.value = value
            validateFields()
        }
    }

    private fun validateFields() {
        viewModelScope.launch {
            isRequirementsState = combine(usernameState, passwordState) { username, password ->
                username.isNotEmpty() && password.isNotEmpty()
            }
        }
    }

    fun onSignIn() {
        viewModelScope.launch {
            val invoke = signInUseCase.signIn(usernameState.value, passwordState.value)
            if (invoke.email.isNotEmpty()) {
                _state.emit(SignInState.NavigateToHome)
            } else {
                _state.emit(SignInState.ShowError( "incorrect username or password!"))
            }
        }
    }

}