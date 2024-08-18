package com.benidict.tamingtemper.screen.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.benidict.tamingtemper.component.activities.ActivitiesListLayout
import com.benidict.tamingtemper.component.header.HeaderViewLayout
import com.benidict.tamingtemper.component.layout.MainViewLayout
import com.benidict.tamingtemper.component.tab.WeeklyTabLayout
import com.benidict.tamingtemper.ui.theme.TamingtemperTheme
import kotlinx.coroutines.Dispatchers

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(navHostController: NavHostController) {
    val viewModel = hiltViewModel<HomeViewModel>()
    LaunchedEffect(Unit) {
        viewModel.loadActivities()
    }
    MainViewLayout(hasTopAppBar = false, navHostController = navHostController, title = "") {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            HeaderViewLayout()
            Spacer(Modifier.height(12.dp))
            WeeklyTabLayout()
            Spacer(Modifier.height(32.dp))
            when(val state = viewModel.state.collectAsState(Dispatchers.IO).value) {
                is HomeState.LoadActivities -> {
                    ActivitiesListLayout(state.data)
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    TamingtemperTheme {
        HomeScreen(navHostController = NavHostController(LocalContext.current))
    }
}