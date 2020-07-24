package com.a100daysofcodehelper.screen.dailyLogger

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.a100daysofcodehelper.dataBase.DailyLog
import com.a100daysofcodehelper.dataBase.DailyLogDao
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*

class DailyLoggerViewModel(
    val database: DailyLogDao,
     application: Application
) : AndroidViewModel(application) {

    //observe the daily Log messages
    // for internal use
    private val _logMessage = MutableLiveData<String>()

    //for external use
    val logMessage: LiveData<String>
        get() = _logMessage

    //to handle submit button clicks
    // for internal use
    private val _submitButtonPressed = MutableLiveData<Boolean>()

    //for external use
    val submitButtonPressed: LiveData<Boolean>
        get() = _submitButtonPressed


    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    //live data to observe and set the today's Log segment
    private var _lastLog = MutableLiveData<DailyLog?>()
    val lastLog: LiveData<DailyLog?>
        get() = _lastLog


    init {
        _logMessage.value = "No Logs yet today"
        _submitButtonPressed.value = false
        initializeTonight()
    }

    private fun initializeTonight() {
        uiScope.launch {
            _lastLog.value = getTodayLogFromDatabase()
        }
    }

    private suspend fun getTodayLogFromDatabase(): DailyLog? {
        return withContext(Dispatchers.IO) {
            database.getLastLog()
        }
    }

    fun submitPressed() {
        _submitButtonPressed.value = true
        uiScope.launch {

            if(checkLastLogDateIsToday()){
               Log.d(" DailyLoggerViewModel","##################################You have already entered Today's Log")
            }else{
                val dailyLog = DailyLog()
                dailyLog.log = _logMessage.value.toString()
                dailyLog.date = getTodayDateString()
                insert(dailyLog)
                _lastLog.value = getTodayLogFromDatabase()
            }
        }
    }

    private fun getTodayDateString() =
        SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().time)

    private fun checkLastLogDateIsToday(): Boolean {
        val lastLog = _lastLog.value
        return if (lastLog != null) {
            val lastLogDate = lastLog.date
            getTodayDateString() == lastLogDate
        } else
            false
    }

    private suspend fun insert(dailyLog: DailyLog) {
        withContext(Dispatchers.IO) {
            database.insert(dailyLog)
        }
    }

    /**
     *set the Today's log in ViewModel
     */
    fun updateDailyLog(dailyLog: String) {
        _logMessage.value = dailyLog
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}