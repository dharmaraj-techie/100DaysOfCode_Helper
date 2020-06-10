package com.a100daysofcodehelper.screen.logsList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.a100daysofcodehelper.dataBase.DailyLogDataBase
import com.a100daysofcodehelper.databinding.FragmentLogBinding
import kotlin.math.log


/**
 * shows the logs as a list
 */
class LogListFragment : Fragment() {
    private lateinit var logListViewModel: LogListViewModel
    private lateinit var logListAdapter: LogListAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(this.activity).application
        val dataSource = DailyLogDataBase.getInstance(application).dailyLogDao

        val logListViewModelFactory = LogListViewModelFactory(dataSource)
        logListViewModel = ViewModelProvider(this,logListViewModelFactory).get(LogListViewModel::class.java)

        // Inflate the layout for this fragment using BindingClass
        val logFragmentBinding = FragmentLogBinding.inflate(inflater, container, false)

        logListAdapter = LogListAdapter()
        //let the recyclerView Know about LogListAdapter
        logFragmentBinding.logList.adapter = logListAdapter

        logListViewModel.logList.observe(this.viewLifecycleOwner, Observer {
            logListAdapter.data = it
        })

        return logFragmentBinding.root
    }
}