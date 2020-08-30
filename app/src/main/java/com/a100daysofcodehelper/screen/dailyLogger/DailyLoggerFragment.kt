package com.a100daysofcodehelper.screen.dailyLogger

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.a100daysofcodehelper.R
import com.a100daysofcodehelper.dataBase.DailyLogDataBase
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

        //get a reference to the application context.
        val application = requireNotNull(this.activity).application

        //get a reference to the DAO of the database
        val dataSource = DailyLogDataBase.getInstance(application).dailyLogDao

        //create a viewModelFactory instance
        val viewModelFactory = DailyLoggerViewModelFactory(dataSource, application)

        //create a viewModel Provider and let the binding class know about it
        viewModel = ViewModelProvider(this, viewModelFactory).get(DailyLoggerViewModel::class.java)
        binding.dailyLoggerViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.eventSubmit.observe(this.viewLifecycleOwner, Observer { isButtonPressed ->
            val logMessage = binding.dailyLogEditTv.text.toString()
            if (isButtonPressed) {
                viewModel.updateDailyLog(logMessage)
//          findNavController().navigate(DailyLoggerFragmentDirections.actionDailyLoggerFragmentToLogFragment(logMessage))
            }
        })

        viewModel.eventEmptyLog.observe(this.viewLifecycleOwner, Observer { isLogEmpty ->
            if (isLogEmpty) {
                Toast.makeText(this.context, "EmptyField", Toast.LENGTH_SHORT).show()
                viewModel.errorToastShown()
            }
        })

        viewModel.eventTweet.observe(this.viewLifecycleOwner, Observer { canTweet ->
            if (canTweet) {
                tweet()
                binding.dailyLogEditTv.text.clear()
                viewModel.tweetCompleted()
            }
        })

        viewModel.submitButtonVisibility.observe(this.viewLifecycleOwner, Observer {
            if (!it) {
                Toast.makeText(
                    this.context,
                    "Great you have completed Today's strike",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
        return binding.root
    }

    private fun tweet() {
        val tweetMsg = binding.dailyLogEditTv.text.toString()
        val tweetUrl =
            ("https://twitter.com/intent/tweet?text=$tweetMsg &hashtags="
                    + "100DaysOfCode")
        val uri: Uri = Uri.parse(tweetUrl)
        startActivity(Intent(Intent.ACTION_VIEW, uri))
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