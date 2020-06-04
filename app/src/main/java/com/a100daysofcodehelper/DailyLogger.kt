package com.a100daysofcodehelper

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


/**
 * Daily logger Fragment
 * its the fragment where user logs the content that he learnt on that day
 * it the home screen of this app
 */
class DailyLogger : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_daily_loger, container, false)
    }
}