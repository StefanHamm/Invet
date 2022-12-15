package com.main.invet.ui.displayboxes

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.main.invet.R

class displayBoxes : Fragment() {

    companion object {
        fun newInstance() = displayBoxes()
    }

    private lateinit var viewModel: DisplayBoxesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_display_boxes, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DisplayBoxesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}