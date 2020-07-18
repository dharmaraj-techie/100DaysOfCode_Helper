package com.a100daysofcodehelper.screen.strike

import android.app.Application
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.a100daysofcodehelper.dataBase.DailyLog
import com.a100daysofcodehelper.dataBase.DailyLogDao
import com.a100daysofcodehelper.getFakeData
import com.a100daysofcodehelper.getSelectedDays
import com.a100daysofcodehelper.screen.dailyLogger.DailyLoggerViewModel
import kotlinx.coroutines.*
import java.util.*

class StrikeViewModel(val database: DailyLogDao) : ViewModel() {

    //to observe the FAB add button clicks
    private val _isAddBtnPressed = MutableLiveData<Boolean>()
    val isAddBtnPressed:LiveData<Boolean>
        get() = _isAddBtnPressed

    //List of selected Dated
    private val _selectedDates = MutableLiveData<List<Calendar>>()
    val selectedDates:LiveData<List<Calendar>>
        get() = _selectedDates


    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main +  viewModelJob)


    init {
        _isAddBtnPressed.value = false
        _selectedDates.value = getSelectedDays()
        insertFakeData()
    }
//insert fake data for testing

    private fun insertFakeData() {
        uiScope.launch {
            val fakeData = getFakeData()
            withContext(Dispatchers.IO){
                val data = fakeData.map {
                    DailyLog(log = it.log,
                        date = it.date
                    )
                }.toTypedArray()
                database.insertAll(*data)
            }
        }
    }

    fun selectedDates(){
        uiScope.launch {
        }
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
/**
 * Binding adapter to set the selected dates in the calendar view
 */
@BindingAdapter("setSelectedDates")
fun setSelectedDates(materialCardView: com.applandeo.materialcalendarview.CalendarView, dates: List<Calendar>){
    materialCardView.selectedDates = dates
}
