package com.example.newsaggregator.data

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.time.temporal.ChronoField
import javax.inject.Inject

class TimeConverter @Inject constructor() {

    @RequiresApi(Build.VERSION_CODES.O)
    fun timeFromString(time: String): String {
        val time1 = DateTimeFormatter.RFC_1123_DATE_TIME.parse(time)
        val month = time1.get(ChronoField.MONTH_OF_YEAR)
        val day = time1.get(ChronoField.DAY_OF_YEAR)

        return "$month $day"
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun currentDate(): String {
        val time1 = LocalDateTime.now()
        val millis = time1.toInstant(ZoneOffset.UTC).toEpochMilli()
        val month = time1.get(ChronoField.MONTH_OF_YEAR)
        val day = time1.get(ChronoField.DAY_OF_YEAR)
        return "$month $day"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun deltaDate(articleDate: String): String {

        try {
            val date = DateTimeFormatter.RFC_1123_DATE_TIME.parse(articleDate)

            val year = date.get(ChronoField.YEAR)
            val month = date.get(ChronoField.MONTH_OF_YEAR)
            val day = date.get(ChronoField.DAY_OF_YEAR)
            val hour = date.get(ChronoField.HOUR_OF_DAY)
            val minutes = date.get(ChronoField.MINUTE_OF_HOUR)

            val currentDate = LocalDateTime.now()

            val currentYear = currentDate.get(ChronoField.YEAR)
            val currentMonth = currentDate.get(ChronoField.MONTH_OF_YEAR)
            val currentDay = currentDate.get(ChronoField.DAY_OF_YEAR)
            val currentHour = currentDate.get(ChronoField.HOUR_OF_DAY)
            val currentMinutes = currentDate.get(ChronoField.MINUTE_OF_HOUR)

            if (year != currentYear) {
                return "on last year"
            }
            if (month != currentMonth) {
                return "on last month"
            }
            if (day != currentDay) {
                val diff = currentDay - day
                if (diff > 1)
                    return "$diff days ago"
                else
                    return "on last day"
            }
            if (hour != currentHour) {
                val diff = currentHour - hour
                if (diff > 1)
                    return "$diff hours ago"
                else
                    return "on last hour"
            }
            if (minutes != currentMinutes) {
                val diff = currentMinutes - minutes
                if (diff > 1)
                    return "$diff minutes ago"
                else
                    return "1 minuteq ago"
            }
            return "now"
        } catch (e: Exception) {
            Log.d("timeParse", "$e.message")
            Log.d("timeParse", articleDate)
            return "error"
        }


    }


}