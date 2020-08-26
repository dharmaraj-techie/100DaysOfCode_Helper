package com.a100daysofcodehelper

import android.util.Log
import androidx.databinding.BindingAdapter
import com.a100daysofcodehelper.dataBase.DailyLog
import com.a100daysofcodehelper.dataBase.DailyLogDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

//fun getFakeData(): List<DailyLog> {
//    val dailyLogs:MutableList<DailyLog> = ArrayList()
//    for (i in 9 downTo 0) {
//        val dailyLog = DailyLog()
//        dailyLog.log = "Fake Data ${i+1}"
//
//        val calendar = Calendar.getInstance()
//        calendar.add(Calendar.DAY_OF_MONTH, i)
//
//        dailyLog.date = SimpleDateFormat("MM/dd/yyyy").format(calendar.time)
//        dailyLogs.add(dailyLog)
//    }
//    return dailyLogs
//}

suspend fun getListOfDatesFromDb(database : DailyLogDao): List<Calendar>?{
    return withContext(Dispatchers.IO) {
        val listOfDatesAsString = database.getAllLogDates()
        val calendars: MutableList<Calendar>? =
            ArrayList()

        for(dateString in listOfDatesAsString){
            val calendar = Calendar.getInstance()
            val sdf = SimpleDateFormat("MM/dd/yyyy")
            if (!dateString.isNullOrEmpty()) {
                calendar.time = sdf.parse(dateString)
                Log.d("StrikeViewModel :", calendar.toString())
                calendars?.add(calendar)
            }
        }
        calendars
    }
}

/**
 * Returns the Today day in the MM/dd/yyyy" format
 * @return Today's date as String
 */
 fun getTodayDateString() =
    SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().time)


/**
 * Binding adapter to set the selected dates in the calendar view
 */
@BindingAdapter("setSelectedDates")
fun setSelectedDates(materialCardView: com.applandeo.materialcalendarview.CalendarView, dates: List<Calendar>?){
    if(!dates.isNullOrEmpty()){
        materialCardView.selectedDates = dates
    }
}