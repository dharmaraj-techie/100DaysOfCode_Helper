package com.a100daysofcodehelper.screen.dailyLog

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.a100daysofcodehelper.dataBase.DailyLog
import com.a100daysofcodehelper.dataBase.DailyLogDao
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

    init {
        _logMessage.value = "No Logs yet today"
        _submitButtonPressed.value = false
    }

    fun submitPressed(){
        _submitButtonPressed.value = true
    }

    /**
    *set the Today's log in ViewModel
     */
    fun updateDailyLog(dailyLog: String){
        _logMessage.value = dailyLog
    }
}