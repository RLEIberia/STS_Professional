package com.rle.STS.ui.Screens.ChecklistScreens

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CheckListStepViewModel : ViewModel() {

    private val checkListSize: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>(0)
    }

    fun getSize(): LiveData<Int> {
        return checkListSize
    }

    fun setSize(size: Int) {
        // Do an asynchronous operation to fetch users.
        checkListSize.value = size
    }

    private val checkListPosition: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>(0)
    }

    fun getPosition(): LiveData<Int> {
        return checkListPosition
    }

    fun setPosition(position: Int) {
        // Do an asynchronous operation to fetch users.
        checkListPosition.value = position
        Log.d("TEST",checkListPosition.value.toString())
    }


    private val showConfirmDialog: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }

    fun getConfirmDialog(): LiveData<Boolean> {
        return showConfirmDialog
    }

    fun setConfirmDialog(openDialog: Boolean) {
        // Do an asynchronous operation to fetch users.
        showConfirmDialog.value = openDialog
    }

}