package com.benidict.tamingtemper.nav.graph

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.benidict.tamingtemper.nav.route.HomeRoute
import com.benidict.tamingtemper.nav.route.SignInRoute
import com.benidict.tamingtemper.screen.home.HomeScreen
import com.benidict.tamingtemper.screen.signin.SignInScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SetUpNavGraph(isLoggedIn: Boolean = false, navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = if (isLoggedIn) HomeRoute else SignInRoute
    ) {
        composable<SignInRoute> {
            SignInScreen(navHostController = navHostController)
        }
        composable<HomeRoute> {
            Log.d("makerChecker", "HomeRoute")
            HomeScreen(navHostController = navHostController) {
                Log.d("makerChecker", "HomeRoute-SignInRoute")
                navHostController.navigate(SignInRoute) {
                    popUpTo(0)
                }
            }
        }
    }
}