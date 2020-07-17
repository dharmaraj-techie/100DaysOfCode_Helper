package com.a100daysofcodehelper.screen.strike

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.card.MaterialCardView
import java.util.*

class StrikeViewModel : ViewModel() {

    //to observe the FAB add button clicks
    private val _isAddBtnPressed = MutableLiveData<Boolean>()
    val isAddBtnPressed:LiveData<Boolean>
        get() = _isAddBtnPressed

    init {
        _isAddBtnPressed.value = false
    }

    fun onAddButtonPressed(){
        _isAddBtnPressed.value = true
    }

    fun navigatedToDailyLogger(){
        _isAddBtnPressed.value = false
    }


    fun getSelectedDays(): List<Calendar> {
        val calendars: MutableList<Calendar> =
            ArrayList()
        for (i in 0..9) {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.DAY_OF_MONTH, i)
            calendars.add(calendar)
        }
        return calendars
    }
}

/**
 * Binding adapter to set the selected dates in the calendar view
 */
@BindingAdapter("setSelectedDates")
fun setSelectedDates(materialCardView: com.applandeo.materialcalendarview.CalendarView, dates: List<Calendar>){
    materialCardView.selectedDates = dates
}
