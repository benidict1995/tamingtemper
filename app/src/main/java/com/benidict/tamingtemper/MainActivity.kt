package com.benidict.tamingtemper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.rememberNavController
import com.benidict.tamingtemper.nav.graph.SetUpNavGraph
import com.benidict.tamingtemper.ui.theme.TamingtemperTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TamingtemperTheme {
                val navController = rememberNavController()
                SetUpNavGraph(
                    isLoggedIn = viewModel.isLoggedIn.collectAsState().value
                    ,navHostController = navController)
            }
        }
    }
}