package com.a100daysofcodehelper

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView

class DailyLoggerViewModel : ViewModel() {
    val logMessage = MutableLiveData<String>()

    init {
        logMessage.value = "No Logs Yet"
    }

}