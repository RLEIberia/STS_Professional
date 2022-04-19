package com.rle.STS.ui.Screens.ChecklistScreens

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CheckListStepViewModel : ViewModel() {

    private val _checkListSize = MutableStateFlow<Int>(0)

    val checkListSize = _checkListSize.asStateFlow()

    fun setSize(size: Int) {
        _checkListSize.value = size
    }

    /*
    fun getSize(): LiveData<Int> {
        return _checkListSize
    }

    fun setSize(size: Int) {
        // Do an asynchronous operation to fetch users.
        _checkListSize.value = size
    }
    */

    private val _checkListPosition = MutableStateFlow<Int>(0)

    val checkListPosition = _checkListPosition.asStateFlow()

    fun setPosition(position: Int) {
        _checkListPosition.value = position
    }

    private val _showConfirmDialog = MutableStateFlow<Boolean>(false)

    val showConfirmDialog = _showConfirmDialog.asStateFlow()

    fun setConfirmDialog(openDialog: Boolean) {
        _showConfirmDialog.value = openDialog
    }

}