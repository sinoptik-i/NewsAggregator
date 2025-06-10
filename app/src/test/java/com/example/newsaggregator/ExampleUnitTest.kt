package com.example.newsaggregator

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.newsaggregator.data.TimeConverter
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoField
import java.util.Calendar
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.Duration.Companion.milliseconds


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        testTime()
//        assertEquals(2,4)
    }
}


fun testTime() {
    val anyTime = "Thu, 29 May 2025 04:00:17 GMT"
//    deltaTime(anyTime)
    deltaTimeHours(anyTime)
//    timeFromString(anyTime)
//    val timeConverter=TimeConverter()
//
//    println(timeConverter.timeFromString(anyTime))
//    println(timeConverter.currentDate())
//    println(timeConverter.deltaDate(anyTime))



}

const val DATE_TAG = "DATE_TAG"

fun toDateFormat(str: String = "Thu, 29 May 2025 04:00:17 GMT"): String {
    val splitedDate = str.split(' ')

//    val simpleDateFormat = "dd MMM yyyy HH:mm:ss"
    val using = "${splitedDate[1]} ${splitedDate[2]} ${splitedDate[3]} ${splitedDate[4]}"
    return using
}



//rabotaet. no kak dostat' millis?
fun timeFromString(time: String): String {
    val time1 = DateTimeFormatter.RFC_1123_DATE_TIME.parse(time)

    val month = time1.get(ChronoField.MONTH_OF_YEAR)
    val day = time1.get(ChronoField.DAY_OF_MONTH)
//    println("year - ${time1.get(ChronoField.YEAR)}")
//    println("days - ${time1.get(ChronoField.DAY_OF_MONTH)}")
    return "$month $day"
}


@SuppressLint("SimpleDateFormat")
fun deltaTimeHours(time: String): Long {
    val TF2 = "EEE, dd MMM yyyy HH:mm:ss z"

//    val sdf: DateFormat = SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z", Locale.US)
    val formatter = SimpleDateFormat(TF2, java.util.Locale.US)//, Locale.US)
//    val dt = DateTime.parse(time, DateTimeFormat.forPattern(TF2))
//    dt.toInstant().minus(DateTime.now().toInstant())
    val nowTime = Calendar.getInstance().timeInMillis
    try {
        val date = formatter.parse(time)
        println( "t ${date!!.time}")
//        Log.d(DATE_TAG, "now Time $date.time")
//        if (date != null) {
//            Log.d(DATE_TAG, "now Time ${(nowTime - date.time) / (1000 * 60 * 60 * 24)}")
//        }
//        println("now Time ${(Date() - date).days}")
    } catch (e: Throwable) {
        System.err.println(e.message)
        e.printStackTrace(System.err)
    }


    return 0
}

