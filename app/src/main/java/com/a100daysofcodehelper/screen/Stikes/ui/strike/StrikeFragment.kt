package com.a100daysofcodehelper.screen.Stikes.ui.strike

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.a100daysofcodehelper.R

class StrikeFragment : Fragment() {

    companion object {
        fun newInstance() = StrikeFragment()
    }

    private lateinit var viewModel: StrikeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.strike_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(StrikeViewModel::class.java)
        // TODO: Use the ViewModel
    }

}