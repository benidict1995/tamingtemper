package com.benidict.tamingtemper

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.benidict.tamingtemper.nav.graph.SetUpNavGraph
import com.benidict.tamingtemper.ui.theme.TamingtemperTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        lifecycleScope.launch {
            setContent {
                TamingtemperTheme {
                    LaunchedEffect(Unit) {
                        viewModel.checkIfLoggedIn()
                    }
                    val navController = rememberNavController()
                    when(val state = viewModel.state.collectAsState(Dispatchers.IO).value) {
                        is MainState.SetUpNavigation -> {
                            SetUpNavGraph(
                                isLoggedIn = state.isLoggedIn
                                ,navHostController = navController)
                        }
                    }
                }
            }
        }
    }
}