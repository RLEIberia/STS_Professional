package com.rle.STS.screens.checklist

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rle.STS.model.JSON.checklistStructure.ChecklistJSON
import com.rle.STS.model.extra.ChecklistPosition
import com.rle.STS.repository.ChecklistRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChecklistViewModel @Inject constructor(
    private val checklistRepository: ChecklistRepository
): ViewModel() {

    //TODO Cambiar a que la posición dependa de la base de datos
    private val _currentPosition: MutableStateFlow<ChecklistPosition> =
        MutableStateFlow(ChecklistPosition())
    val currentPosition = _currentPosition.asStateFlow()


    private val _checklistJSON: MutableStateFlow<ChecklistJSON> = MutableStateFlow(ChecklistJSON())
    val checklist = _checklistJSON.asStateFlow()

    fun extractChecklist(fileName: String, context: Context) =
        viewModelScope.launch {
            val jsonData =checklistRepository.getJson(
                context = context,
                fileName = fileName
            )
            Log.d("JSON", jsonData.toString())
            _checklistJSON.value = checklistRepository.extractChecklist(
                jsonChecklist = jsonData
            )
            Log.d("CKVAL", _checklistJSON.value.toString())
//            val jsonData = GetJsonDataFromAsset(context = context, fileName)
//            _checklistData.value = com.rle.STS.logic.json.extractChecklist(jsonData)

            Log.d("CKVIEWMODEL", checklist.value.checklistData!!.steps[currentPosition.value.step].views[currentPosition.value.view].viewType)

        }

//    fun

}

//private val _checkListSize = MutableStateFlow<Int>(0)
//val checkListSize = _checkListSize.asStateFlow()
//
//fun setSize(size: Int) {
//    _checkListSize.value = size
//}
//
///*
//fun getSize(): LiveData<Int> {
//    return _checkListSize
//}
//
//fun setSize(size: Int) {
//    // Do an asynchronous operation to fetch users.
//    _checkListSize.value = size
//}
//*/
//
//private val _checkListPosition = MutableStateFlow<Int>(0)
//val checkListPosition = _checkListPosition.asStateFlow()
//
//fun setPosition(position: Int) {
//    _checkListPosition.value = position
//}
//
//private val _showConfirmDialog = MutableStateFlow<Boolean>(false)
//
//val showConfirmDialog = _showConfirmDialog.asStateFlow()
//
//fun setConfirmDialog(openDialog: Boolean) {
//    _showConfirmDialog.value = openDialog
//}
//
//private val _viewChosen = MutableStateFlow<String>("")
//val viewChosen = _viewChosen.asStateFlow()