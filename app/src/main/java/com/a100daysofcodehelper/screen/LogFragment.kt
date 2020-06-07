package com.a100daysofcodehelper.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.a100daysofcodehelper.databinding.FragmentLogBinding


/**
 * shows the logs as a list
 */
class LogFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val logFragmentBinding = FragmentLogBinding.inflate(inflater, container, false)
        //get the passed argument
        val args =
            LogFragmentArgs.fromBundle(requireArguments())
        //set the textView by the String got from the argument
        logFragmentBinding.dailyLogTv.text = args.dailyLog
        return logFragmentBinding.root
    }

}