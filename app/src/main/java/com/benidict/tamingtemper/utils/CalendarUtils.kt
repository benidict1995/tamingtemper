package com.benidict.tamingtemper.utils

import android.os.Build
import androidx.annotation.RequiresApi
import com.benidict.domain.DATE_FORMAT_1
import com.benidict.domain.DATE_FORMAT_2
import com.benidict.domain.model.CalendarUIModel
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Calendar
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
fun getDayToday(): LocalDate = LocalDate.now()


fun daysInWeekArray(): ArrayList<CalendarUIModel> {
    val daysInWeekArray = ArrayList<CalendarUIModel>()
    val dateFormat: DateFormat = SimpleDateFormat(DATE_FORMAT_1, Locale.getDefault())
    val dayFormat: DateFormat = SimpleDateFormat(DATE_FORMAT_2, Locale.getDefault())
    val calendar = Calendar.getInstance()
    calendar.firstDayOfWeek = Calendar.MONDAY
    calendar[Calendar.DAY_OF_WEEK] = Calendar.MONDAY
    for (i in 0..6) {
        daysInWeekArray.add(
            CalendarUIModel(
                days = dayFormat.format(calendar.time).toString(),
                date = dateFormat.format(calendar.time).toString()

            )
        )
        calendar.add(Calendar.DAY_OF_MONTH, 1)
    }
    return daysInWeekArray
}