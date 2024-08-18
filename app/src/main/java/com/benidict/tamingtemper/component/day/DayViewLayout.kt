package com.benidict.tamingtemper.component.day

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
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
fun DayViewLayout(calendar: CalendarUIModel) {
    val isDateToday = calendar.date == getDayToday().toString()
    Column(
        modifier = Modifier.wrapContentWidth(Alignment.CenterHorizontally)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 13.5.dp)
        ) {
            Image(
                painter = painterResource(
                    if (isDateToday)
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
                color = if (isDateToday) RoyalBlue else SilverChalice,
                fontSize = 12.sp,
                modifier = Modifier,
                text = calendar.days.toUpperCase(Locale.ENGLISH)
            )
        }
        LinearProgressIndicator(
            modifier = Modifier
                .height(1.dp)
                .width(50.dp),
            progress = if (isDateToday) 100f else 0f
        )
    }
}