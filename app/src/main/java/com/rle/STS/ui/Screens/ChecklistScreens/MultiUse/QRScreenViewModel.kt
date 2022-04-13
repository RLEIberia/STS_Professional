package com.rle.STS.ui.Screens.ChecklistScreens.MultiUse

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QRScreenViewModel : ViewModel() {

    private val QRText: MutableLiveData<String> by lazy {
        MutableLiveData<String>().also {
            setText("")
        }
    }

    fun getText(): LiveData<String> {
        return QRText
    }

    fun setText(text: String) {
        // Do an asynchronous operation to fetch users.
    }

}