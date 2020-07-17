package com.a100daysofcodehelper.screen.Stikes.ui.strike

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.a100daysofcodehelper.R
import com.a100daysofcodehelper.databinding.DailyLogListItemBinding
import com.a100daysofcodehelper.databinding.FragmentStrikeBinding
import kotlinx.android.synthetic.main.fragment_strike.*
import java.util.*

class StrikeFragment : Fragment() {

    private lateinit var viewModel: StrikeViewModel
    private lateinit var binding: FragmentStrikeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(StrikeViewModel::class.java)
        binding = FragmentStrikeBinding.inflate(inflater,container,false)

        binding.fabAddBtn.setOnClickListener(View.OnClickListener {
            findNavController().navigate(StrikeFragmentDirections.actionStrikeFragmentToDailyLoggerFragment())
        })

        calendarView.selectedDates = getSelectedDays()
        return binding.root
    }


    private fun getSelectedDays(): List<Calendar> {
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