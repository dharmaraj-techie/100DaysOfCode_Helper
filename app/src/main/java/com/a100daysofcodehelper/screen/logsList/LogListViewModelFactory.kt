package com.a100daysofcodehelper.screen.logsList

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.a100daysofcodehelper.dataBase.DailyLogDao
import com.a100daysofcodehelper.screen.dailyLogger.DailyLoggerViewModel

class LogListViewModelFactory (private val dataSource: DailyLogDao) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LogListViewModel::class.java)) {
            return LogListViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}