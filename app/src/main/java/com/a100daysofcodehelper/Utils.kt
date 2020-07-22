package com.a100daysofcodehelper

import androidx.databinding.BindingAdapter
import com.a100daysofcodehelper.dataBase.DailyLog
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




/**
 * Binding adapter to set the selected dates in the calendar view
 */
@BindingAdapter("setSelectedDates")
fun setSelectedDates(materialCardView: com.applandeo.materialcalendarview.CalendarView, dates: List<Calendar>?){
    if(!dates.isNullOrEmpty()){
        materialCardView.selectedDates = dates
    }
}