package com.a100daysofcodehelper

import com.a100daysofcodehelper.dataBase.DailyLog
import java.text.SimpleDateFormat
import java.util.*

fun getFakeData(): List<DailyLog> {
    val dailyLogs:MutableList<DailyLog> = ArrayList()
    for (i in 9 downTo 0) {
        val dailyLog = DailyLog()
        dailyLog.log = "Fake Data ${i+1}"

        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_MONTH, i)

        dailyLog.date = SimpleDateFormat("MM/dd/yyyy").format(calendar.time)
        dailyLogs.add(dailyLog)
    }
    return dailyLogs
}

fun getSelectedDays(): List<Calendar> {






    val calendars: MutableList<Calendar> =
        ArrayList()
    for (i in 0..9) {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_MONTH, i)
        calendars.add(calendar)
    }
    return calendars
}