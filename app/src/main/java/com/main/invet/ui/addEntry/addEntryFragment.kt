package com.main.invet.ui.addEntry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.main.invet.databinding.FragmentAddentryBinding

class addEntryFragment:Fragment() {
    private var _binding: FragmentAddentryBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(addEntryViewmodel::class.java)

        _binding = FragmentAddentryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textAddentry
        galleryViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}