package com.a100daysofcodehelper.screen.logsList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.a100daysofcodehelper.dataBase.DailyLog
import com.a100daysofcodehelper.dataBase.DailyLogDao
import kotlinx.coroutines.*

class LogViewModel(
    val database: DailyLogDao
) : ViewModel() {

    private val _logList = MutableLiveData<List<DailyLog>>()
    val logList: LiveData<List<DailyLog>>
        get() = _logList

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main +  viewModelJob)

    init {
        initialiseLogList()
    }

    private fun initialiseLogList() {
        uiScope.launch {
             withContext(Dispatchers.IO) {
                 _logList.value = database.getAllLogs()
            }
        }
    }
}