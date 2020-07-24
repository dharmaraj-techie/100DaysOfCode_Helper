package com.a100daysofcodehelper.screen.strike

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.a100daysofcodehelper.dataBase.DailyLogDao
import com.a100daysofcodehelper.getListOfDatesFromDb
import kotlinx.coroutines.*
import java.util.*

class StrikeViewModel(val database: DailyLogDao) : ViewModel() {

    //to observe the FAB add button clicks
    private val _isAddBtnPressed = MutableLiveData<Boolean>()
    val isAddBtnPressed: LiveData<Boolean>
        get() = _isAddBtnPressed

    //to observe the selected dates and update in the strikes UI
    private val _selectedDaysList = MutableLiveData<List<Calendar>>()
    val selectedDaysList: LiveData<List<Calendar>>
        get() = _selectedDaysList

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    init {
        _isAddBtnPressed.value = false
        setSelectedDates()
    }

    private fun setSelectedDates() {
        uiScope.launch {
            _selectedDaysList.value = getListOfDatesFromDb(database)
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

