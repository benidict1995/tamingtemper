package com.benidict.tamingtemper.component.day

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.benidict.domain.model.CalendarUIModel
import com.benidict.tamingtemper.R
import com.benidict.tamingtemper.ui.theme.RoyalBlue
import com.benidict.tamingtemper.ui.theme.SilverChalice
import com.benidict.tamingtemper.ui.theme.euclidCircularFont
import com.benidict.tamingtemper.utils.getDayToday
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DayViewLayout(calendar: CalendarUIModel, selectedDate: String, onDateSelected: (String) -> Unit) {
    val dateSelected = calendar.date == selectedDate
    Log.d("makerChecker", "dateSelected:$dateSelected, calendar.date:${calendar.date}, selectedDate:$selectedDate")
    Column(
        modifier = Modifier.wrapContentWidth(Alignment.CenterHorizontally)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 13.5.dp).clickable {
                Log.d("makerChecker", "dated:${calendar.date}")
                onDateSelected(calendar.date)
            }
        ) {
            Image(
                painter = painterResource(
                    if (dateSelected)
                        R.drawable.ic_day_active else R.drawable.ic_day_inactive
                ),
                contentDescription = ""
            )
            Spacer(
                modifier = Modifier.height(8.dp)
            )
            Text(
                fontFamily = euclidCircularFont,
                fontWeight = FontWeight.Medium,
                color = if (dateSelected) RoyalBlue else SilverChalice,
                fontSize = 12.sp,
                modifier = Modifier,
                text = calendar.days.toUpperCase(Locale.ENGLISH)
            )
        }
        LinearProgressIndicator(
            modifier = Modifier
                .height(1.dp)
                .width(50.dp),
            progress = if (dateSelected) 100f else 0f
        )
    }
}