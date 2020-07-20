package com.a100daysofcodehelper.screen.strike

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

class StrikeViewModel(val database: DailyLogDao) : ViewModel() {

    //to observe the FAB add button clicks
    private val _isAddBtnPressed = MutableLiveData<Boolean>()
    val isAddBtnPressed:LiveData<Boolean>
        get() = _isAddBtnPressed

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main +  viewModelJob)

    val selectedDates = database.getAllLogDates()

    init {
        _isAddBtnPressed.value = false
    }



    fun getFakeSelectedDays(): List<Calendar> {
        val calendars: MutableList<Calendar> =
            ArrayList()
        for (i in 0..9) {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.DAY_OF_MONTH, i)
            calendars.add(calendar)
        }
        return calendars
    }

    fun onAddButtonPressed(){
        _isAddBtnPressed.value = true
    }

    fun navigatedToDailyLogger(){
        _isAddBtnPressed.value = false
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}


class StrikeViewModelFactory(private val dataSource: DailyLogDao
) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StrikeViewModel::class.java)) {
            return StrikeViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

