package com.main.invet.ui.displayitems

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.main.invet.R

class displayItems : Fragment() {

    companion object {
        fun newInstance() = displayItems()
    }

    private lateinit var viewModel: DisplayItemsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_display_items, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DisplayItemsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}