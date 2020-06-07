package com.a100daysofcodehelper.screen.dailyLog

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.a100daysofcodehelper.dataBase.DailyLog
import com.a100daysofcodehelper.dataBase.DailyLogDao

class DailyLoggerViewModelFactory(private val database: DailyLogDao,
                                  private val application: Application) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DailyLoggerViewModel::class.java)) {
            return DailyLoggerViewModel(database, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}