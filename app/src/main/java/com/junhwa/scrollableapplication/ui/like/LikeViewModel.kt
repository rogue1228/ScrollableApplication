package com.junhwa.scrollableapplication.ui.like

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LikeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is like Fragment"
    }
    val text: LiveData<String> = _text
}