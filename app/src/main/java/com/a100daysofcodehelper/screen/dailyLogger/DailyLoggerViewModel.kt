package com.a100daysofcodehelper.screen.dailyLogger

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.a100daysofcodehelper.dataBase.DailyLog
import com.a100daysofcodehelper.dataBase.DailyLogDao
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*

class DailyLoggerViewModel(val database: DailyLogDao,
                           application: Application
) : AndroidViewModel(application) {

    // for internal use
    private val _logMessage = MutableLiveData<String>()
    //for external use
    val logMessage :LiveData<String>
            get() = _logMessage

     // for internal use
    private val _submitButtonPressed = MutableLiveData<Boolean>()
    //for external use
    val submitButtonPressed :LiveData<Boolean>
            get() = _submitButtonPressed

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main +  viewModelJob)

    private var _todayLog = MutableLiveData<DailyLog?>()
    val todayLog : LiveData<DailyLog?>
        get() = _todayLog


    init {
        _logMessage.value = "No Logs yet today"
        _submitButtonPressed.value = false
        initializeTonight()
    }

    private fun initializeTonight() {
        uiScope.launch {
            _todayLog.value = getTodayLogFromDatabase()
        }
    }

    private suspend fun getTodayLogFromDatabase(): DailyLog? {
        return withContext(Dispatchers.IO) {
            database.getLastLog()
        }
    }

    fun submitPressed(){
        _submitButtonPressed.value = true
        uiScope.launch {
            val dailyLog = DailyLog()
            dailyLog.log = _logMessage.value.toString()
            dailyLog.date = SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().time)
            insert(dailyLog)
            _todayLog.value = getTodayLogFromDatabase()
        }
    }

    private suspend fun insert(dailyLog: DailyLog){
        withContext(Dispatchers.IO) {
            database.insert(dailyLog)
        }
    }

    /**
    *set the Today's log in ViewModel
     */
    fun updateDailyLog(dailyLog: String){
        _logMessage.value = dailyLog
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}