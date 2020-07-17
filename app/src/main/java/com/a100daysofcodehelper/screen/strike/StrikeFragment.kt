package com.a100daysofcodehelper.screen.strike

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.a100daysofcodehelper.databinding.FragmentStrikeBinding
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

        binding.strikeViewModel = viewModel

        binding.lifecycleOwner = this

        //Add button is pressed we need to navigate to the dailyLogger activity
        viewModel.isAddBtnPressed.observe(this.viewLifecycleOwner, androidx.lifecycle.Observer { isPressed ->
            if(isPressed){
                findNavController().navigate(StrikeFragmentDirections.actionStrikeFragmentToDailyLoggerFragment())
                viewModel.navigatedToDailyLogger()
            }
        })

//        binding.calendarView.selectedDates = viewModel.getSelectedDays()
        return binding.root
    }



}