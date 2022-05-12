package com.rle.STS.screens.main

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rle.STS.model.JSON.checklistStructure.Checklist
import com.rle.STS.repository.ChecklistRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val checklistRepository: ChecklistRepository): ViewModel() {


    /* TODO SACAR ESTO - PRUEBAS */

    private val _checklistData: MutableStateFlow<Checklist> = MutableStateFlow(Checklist())
    val checklistData = _checklistData.asStateFlow()

    fun extractChecklist(fileName: String, context: Context) =
        viewModelScope.launch {
            val jsonData =checklistRepository.getJson(
                context = context,
                fileName = fileName
            )
            _checklistData.value = checklistRepository.extractChecklist(
                jsonChecklist = jsonData
            )
        }

    /* --------------------------------------------- */
}