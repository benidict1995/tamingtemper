package com.benidict.tamingtemper.screen.home

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavHostController
import com.benidict.domain.model.LevelDTO
import com.benidict.tamingtemper.component.activities.ActivitiesListLayout
import com.benidict.tamingtemper.component.header.HeaderViewLayout
import com.benidict.tamingtemper.component.layout.MainViewLayout
import com.benidict.tamingtemper.component.tab.WeeklyTabLayout
import com.benidict.tamingtemper.nav.route.HomeRoute
import com.benidict.tamingtemper.nav.route.SignInRoute
import com.benidict.tamingtemper.ui.theme.TamingtemperTheme
import kotlinx.coroutines.Dispatchers

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(navHostController: NavHostController, onLogOut: () -> Unit) {
    val viewModel = hiltViewModel<HomeViewModel>()
    val lifecycleOwner = LocalLifecycleOwner.current
    var activitiesState = remember { mutableStateListOf<LevelDTO>() }
    LaunchedEffect(Unit) {
        viewModel.loadActivities()
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.state.collect { state ->
                when (state) {
                    is HomeState.LoadActivities -> {
                        activitiesState.addAll(state.data)
                    }

                    is HomeState.LogOut -> {
                        Log.d("makerChecker", "LogOut")
                        onLogOut()
                    }
                }
            }
        }
    }
    MainViewLayout(hasTopAppBar = false, navHostController = navHostController, title = "") {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            HeaderViewLayout {
                viewModel.logOut()
            }
            Spacer(Modifier.height(12.dp))
            WeeklyTabLayout()
            Spacer(Modifier.height(32.dp))
            ActivitiesListLayout(activitiesState)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    TamingtemperTheme {
        HomeScreen(navHostController = NavHostController(LocalContext.current)) {

        }
    }
}