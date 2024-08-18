package com.benidict.tamingtemper.nav.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.benidict.tamingtemper.nav.route.HomeRoute
import com.benidict.tamingtemper.nav.route.SignInRoute
import com.benidict.tamingtemper.screen.home.HomeScreen
import com.benidict.tamingtemper.screen.signin.SignInScreen

@Composable
fun SetUpNavGraph(isLoggedIn: Boolean = false, navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = SignInRoute
    ) {
        composable<SignInRoute> {
            if (isLoggedIn) {
                HomeScreen(navHostController = navHostController)
            } else {
                SignInScreen(navHostController =  navHostController)
            }
        }
        composable<HomeRoute> {
            HomeScreen(navHostController = navHostController)
        }
    }
}