package com.benidict.tamingtemper.component.tab

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
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