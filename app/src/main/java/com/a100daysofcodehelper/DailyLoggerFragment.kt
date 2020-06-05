package com.a100daysofcodehelper

import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.a100daysofcodehelper.databinding.FragmentDailyLogerBinding
import kotlinx.android.synthetic.main.fragment_daily_loger.*


/**
 * Daily logger Fragment
 * its the fragment where user logs the content that he learnt on that day
 * it the home screen of this app
 */
class DailyLoggerFragment : Fragment() {

    private lateinit var binding: FragmentDailyLogerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        // Inflate the layout for this fragment using the binding class
        binding = FragmentDailyLogerBinding.inflate(inflater, container, false)

        //set a clickListener to the button to navigate to the logFragment
        binding.submitBtn.setOnClickListener {
            val logMessage: String = binding.dailyLogEditTv.text.toString()
            Toast.makeText(context, logMessage, Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_dailyLoggerFragment_to_logFragment)
        }

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