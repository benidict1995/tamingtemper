package com.benidict.tamingtemper.screen.signin

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.benidict.tamingtemper.R
import com.benidict.tamingtemper.component.inputtext.InputViewLayout
import com.benidict.tamingtemper.component.layout.MainViewLayout
import com.benidict.tamingtemper.nav.route.HomeRoute
import java.util.Locale

@Composable
fun SignInScreen(navHostController: NavHostController) {
    val viewModel = hiltViewModel<SignInViewModel>()
    val context  = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.state.collect { state ->
            when(state) {
                SignInState.NavigateToHome -> {
                    navHostController.navigate(HomeRoute) {
                        popUpTo(0)
                    }
                }
                is SignInState.ShowError -> {
                    Toast.makeText(context, state.msg, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    MainViewLayout(hasTopAppBar = false, navHostController, title = "") {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Spacer(
                modifier = Modifier.height(100.dp)
            )
            Box(
                contentAlignment = Alignment.TopCenter,
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        modifier = Modifier.height(100.dp).width(100.dp),
                        painter = painterResource(R.drawable.ic_logo),
                        contentDescription = ""
                    )
                    Spacer(
                        modifier = Modifier.height(20.dp)
                    )
                    InputViewLayout(
                        value = viewModel.usernameState.collectAsState().value,
                        label = stringResource(R.string.label_username)
                    ) { value ->
                        viewModel.onUsernameChanged(value)
                    }

                    InputViewLayout(
                        isPassword = true,
                        value = viewModel.passwordState.collectAsState().value,
                        label = stringResource(R.string.label_password)
                    ) { value ->
                        viewModel.onPasswordChanged(value)
                    }

                    Spacer(
                        modifier = Modifier.height(50.dp)
                    )
                    Button(
                        modifier = Modifier
                            .height(56.dp)
                            .fillMaxWidth().padding(
                            start = 30.dp,
                            end = 30.dp
                        ),
                        onClick = {
                           viewModel.onSignIn()
                        },
                        enabled = viewModel.isRequirementsState.collectAsState(false).value
                    ) {
                        Text(text = stringResource(R.string.sign_in).toUpperCase(Locale.ENGLISH))
                    }
                }
            }
        }
    }
}