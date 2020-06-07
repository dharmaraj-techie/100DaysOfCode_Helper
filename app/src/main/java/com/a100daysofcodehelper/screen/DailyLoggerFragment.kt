package com.a100daysofcodehelper.screen

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.a100daysofcodehelper.R
import com.a100daysofcodehelper.databinding.FragmentDailyLogerBinding


/**
 * Daily logger Fragment
 * its the fragment where user logs the content that he learnt on that day
 * it the home screen of this app
 */
class DailyLoggerFragment : Fragment() {

    private lateinit var binding: FragmentDailyLogerBinding
    private lateinit var viewModel: DailyLoggerViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        // Inflate the layout for this fragment using the binding class
        binding = FragmentDailyLogerBinding.inflate(inflater, container, false)

        //create a viewModel Provider and let the binding class know about it
        viewModel = ViewModelProvider(this).get(DailyLoggerViewModel::class.java)
        binding.dailyLoggerViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.submitButtonPressed.observe(this.viewLifecycleOwner, Observer { isButtonPressed ->
            if (isButtonPressed) {
                viewModel.updateDailyLog(binding.dailyLogEditTv.text.toString())
//            findNavController().navigate(DailyLoggerFragmentDirections.actionDailyLoggerFragmentToLogFragment(logMessage))
            }
        })

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        return inflater.inflate(R.menu.main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, findNavController())
                || super.onOptionsItemSelected(item)
    }
}