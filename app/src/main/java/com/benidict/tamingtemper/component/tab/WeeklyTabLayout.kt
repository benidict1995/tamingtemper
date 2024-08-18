package com.benidict.tamingtemper.component.tab

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import com.benidict.tamingtemper.component.day.DayViewLayout
import com.benidict.tamingtemper.utils.daysInWeekArray

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeeklyTabLayout() {
    LazyRow {
        items(daysInWeekArray()) { calendar ->
            DayViewLayout(calendar)
        }
    }
}