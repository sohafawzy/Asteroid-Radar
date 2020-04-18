package com.udacity.asteroidradar.utils

import java.text.SimpleDateFormat
import java.util.*

object RequestHelper {

    private val today: Calendar = Calendar.getInstance()

    private val nextWeek: Calendar
        get() {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.DATE, 7)
            return calendar
        }

    val start: String =
        getFormattedDate(
            today
        )
    val end: String =
        getFormattedDate(
            nextWeek
        )

    private fun getFormattedDate(calendar: Calendar): String {
        return SimpleDateFormat("yyyy-MM-dd", Locale.US).format(calendar.time)
    }
}

