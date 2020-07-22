package com.a100daysofcodehelper.screen.strike

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.a100daysofcodehelper.dataBase.DailyLog
import com.a100daysofcodehelper.dataBase.DailyLogDao
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class StrikeViewModel(val database: DailyLogDao) : ViewModel() {

    //to observe the FAB add button clicks
    private val _isAddBtnPressed = MutableLiveData<Boolean>()
    val isAddBtnPressed: LiveData<Boolean>
        get() = _isAddBtnPressed

    //to observe the FAB add button clicks
    private val _selectedDaysList = MutableLiveData<List<Calendar>>()
    val selectedDaysList: LiveData<List<Calendar>>
        get() = _selectedDaysList

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    init {
        _isAddBtnPressed.value = false
        setSelectedDates()
    }


    fun getFakeSelectedDays() {
        val calendars: MutableList<Calendar> =
            ArrayList()
        for (i in 0..9) {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.DAY_OF_MONTH, i)
            calendars.add(calendar)
        }
        _selectedDaysList.value = calendars
    }

    fun setSelectedDates() {
        uiScope.launch {
            _selectedDaysList.value = getSelectedDatesFromDb()
        }
    }

    private suspend fun getSelectedDatesFromDb(): List<Calendar>?{
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

    fun onAddButtonPressed() {
        _isAddBtnPressed.value = true
    }

    fun navigatedToDailyLogger() {
        _isAddBtnPressed.value = false
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}


class StrikeViewModelFactory(
    private val dataSource: DailyLogDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StrikeViewModel::class.java)) {
            return StrikeViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

