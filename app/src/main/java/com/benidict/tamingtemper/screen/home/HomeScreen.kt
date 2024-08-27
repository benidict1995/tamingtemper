package com.benidict.tamingtemper.screen.home

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavHostController
import com.benidict.domain.model.CalendarUIModel
import com.benidict.domain.model.LevelDTO
import com.benidict.tamingtemper.component.activities.ActivitiesListLayout
import com.benidict.tamingtemper.component.empty.EmptyActivitiesViewLayout
import com.benidict.tamingtemper.component.footer.FooterViewLayout
import com.benidict.tamingtemper.component.header.HeaderViewLayout
import com.benidict.tamingtemper.component.layout.MainViewLayout
import com.benidict.tamingtemper.component.tab.WeeklyTabLayout
import com.benidict.tamingtemper.ui.theme.TamingtemperTheme
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(navHostController: NavHostController, onLogOut: () -> Unit) {
    val viewModel = hiltViewModel<HomeViewModel>()
    val lifecycleOwner = LocalLifecycleOwner.current
    val isLoading = remember { mutableStateOf(true) }
    val activitiesState = remember { mutableStateListOf<LevelDTO>() }
    val weeklyDates = remember { mutableStateListOf<CalendarUIModel>() }
    val selectedDate = remember { mutableStateOf(viewModel.selectedDate.value) }
    LaunchedEffect(Unit) {

        launch {
            viewModel.daysInWeek.onEach {
                weeklyDates.clear()
                weeklyDates.addAll(it)
            }.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                .launchIn(lifecycleOwner.lifecycleScope)
        }

        launch {
            viewModel.selectedDate.onEach { date ->
                isLoading.value = true
                selectedDate.value = date
                viewModel.loadActivities()
            }.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                .launchIn(lifecycleOwner.lifecycleScope)
        }

        launch {
            viewModel.state.onEach { state ->
                when (state) {
                    is HomeState.LoadActivities -> {
                        activitiesState.clear()
                        activitiesState.addAll(state.data)
                        isLoading.value = false
                    }

                    is HomeState.LogOut -> {
                        onLogOut()
                    }
                }
            }
                .flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                .launchIn(lifecycleOwner.lifecycleScope)

        }
    }
    MainViewLayout(hasTopAppBar = false, navHostController = navHostController, title = "") { paddingValue ->
        ConstraintLayout(modifier = Modifier.fillMaxSize().padding(paddingValue)) {
            val (activitiesLayout, footerLayout, loaderLayout, emptyLayout, headerLayout) = createRefs()

            Column(
                modifier = Modifier
                    .constrainAs(
                        headerLayout
                    ) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }.then(Modifier.fillMaxWidth().padding(horizontal = 16.dp))
            ) {
                HeaderViewLayout {
                    viewModel.logOut()
                }
                Spacer(Modifier.height(12.dp))
                WeeklyTabLayout(weeklyDates, selectedDate.value) {
                    viewModel.selectedDate.value = it
                }
                Spacer(Modifier.height(32.dp))
            }

            if (activitiesState.isEmpty() && isLoading.value.not()) {
                EmptyActivitiesViewLayout(
                    modifier = Modifier.constrainAs(emptyLayout) {
                        top.linkTo(headerLayout.bottom)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                )
            }
            if (isLoading.value) {
                CircularProgressIndicator(
                    modifier = Modifier.constrainAs(loaderLayout) {
                        top.linkTo(headerLayout.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                )
            } else {
                ActivitiesListLayout(modifier = Modifier.constrainAs(activitiesLayout) {
                    top.linkTo(headerLayout.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(footerLayout.top)
                    height = Dimension.fillToConstraints
                }, activitiesState)

                FooterViewLayout(
                    modifier = Modifier.constrainAs(footerLayout) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                )
            }
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