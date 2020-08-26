package com.a100daysofcodehelper.screen.dailyLogger

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.a100daysofcodehelper.dataBase.DailyLog
import com.a100daysofcodehelper.dataBase.DailyLogDao
import com.a100daysofcodehelper.getTodayDateString
import kotlinx.coroutines.*

class DailyLoggerViewModel(
    val database: DailyLogDao,
    application: Application
) : AndroidViewModel(application) {

    //observe the daily Log messages
    private val _logMessage = MutableLiveData<String>()
    val logMessage: LiveData<String>
        get() = _logMessage

    //to handle submit button clicks
    private val _submitButtonPressed = MutableLiveData<Boolean>()
    val submitButtonPressed: LiveData<Boolean>
        get() = _submitButtonPressed

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    //live data to observe and set the today's Log segment
    private var _lastLog = MutableLiveData<DailyLog?>()
    val lastLog: LiveData<DailyLog?>
        get() = _lastLog

    /**
     * make the submit button disabled when the today's log has been entered preventing from additional entries
     */
    val submitButtonVisibility = Transformations.map(_lastLog){lastLog ->
        Log.d("DailyLoggerViewModel", "true LastLog date: ${lastLog?.date}  Today's Date: ${getTodayDateString()} \"")
        lastLog?.date != getTodayDateString()
    }

    init {
        _logMessage.value = "No Logs yet today"
        _submitButtonPressed.value = false
        initializeRecentLog()
    }

    private fun initializeRecentLog() {
        uiScope.launch {
            _lastLog.value = getRecentLogFromDatabase()
        }
    }

    /**
     * @returns the last entered(i.e recent) Log
     */
    private suspend fun getRecentLogFromDatabase(): DailyLog? {
        return withContext(Dispatchers.IO) {
            database.getLastLog()
        }
    }

    fun submitPressed() {
        _submitButtonPressed.value = true
        uiScope.launch {
            val dailyLog = DailyLog()
            dailyLog.log = _logMessage.value.toString()
            dailyLog.date = getTodayDateString()
            insert(dailyLog)
            _lastLog.value = getRecentLogFromDatabase()
        }
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