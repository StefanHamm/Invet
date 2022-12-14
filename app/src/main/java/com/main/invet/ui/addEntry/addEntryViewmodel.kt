package com.main.invet.ui.addEntry

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class addEntryViewmodel:ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is add entry fragment"
    }
    val text: LiveData<String> = _text
}