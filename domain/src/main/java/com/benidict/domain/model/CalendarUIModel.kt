package com.benidict.domain.model

data class CalendarUIModel(
    val days: String = "",
    val date: String = "",
    val isSelected: Boolean = false
) {
    companion object {
        fun empty() = CalendarUIModel(
            days = "",
            date = "",
            isSelected = false
        )
    }
}