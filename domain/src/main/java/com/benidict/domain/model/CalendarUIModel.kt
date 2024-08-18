package com.benidict.domain.model

data class CalendarUIModel(
    val days: String = "",
    val date: String = ""
) {
    companion object {
        fun empty() = CalendarUIModel(
            days = "",
            date = ""
        )
    }
}