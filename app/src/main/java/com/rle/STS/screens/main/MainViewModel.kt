package com.rle.STS.screens.main

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rle.STS.data.DataOrException
import com.rle.STS.model.APIs.projects.ProjectsResponse
import com.rle.STS.model.JSON.checklistStructure.ChecklistJSON
import com.rle.STS.repository.APIRepository
import com.rle.STS.repository.ChecklistRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val checklistRepository: ChecklistRepository, private val apiRepository: APIRepository): ViewModel() {


    /* TODO SACAR ESTO - PRUEBAS */

    private val _checklistJSONData: MutableStateFlow<ChecklistJSON> = MutableStateFlow(ChecklistJSON())
    val checklistData = _checklistJSONData.asStateFlow()

    fun extractChecklist(fileName: String, context: Context) =
        viewModelScope.launch {
            val jsonData =checklistRepository.getJson(
                context = context,
                fileName = fileName
            )
            _checklistJSONData.value = checklistRepository.extractChecklist(
                jsonChecklist = jsonData
            )


//            val jsonData = GetJsonDataFromAsset(context = context, fileName)
//            _checklistData.value = com.rle.STS.logic.json.extractChecklist(jsonData)

        }

    /* --------------------------------------------- */

    private val _APIprojectResponse: MutableStateFlow<DataOrException<ProjectsResponse, Boolean, Exception>> =
        MutableStateFlow(DataOrException(null, true, null))

    val APIprojectResponse = _APIprojectResponse.asStateFlow()

    fun getProjects() {
        viewModelScope.launch {
            _APIprojectResponse.value.loading = true
            Log.d("API_L", _APIprojectResponse.value.loading.toString())
            Log.d("API_D", _APIprojectResponse.value.data.toString())
            _APIprojectResponse.value = apiRepository.getProjects()
            if(_APIprojectResponse.value.data.toString().isNotEmpty()||_APIprojectResponse.value.e.toString().isNotEmpty()) {
                _APIprojectResponse.value.loading = false
                Log.d("LOAD", _APIprojectResponse.value.loading.toString())
                Log.d("EX", _APIprojectResponse.value.e.toString())
            }
            Log.d("API_L", _APIprojectResponse.value.loading.toString())
            Log.d("API_E", _APIprojectResponse.value.e.toString())
            Log.d("API_D", _APIprojectResponse.value.data.toString())
        }
    }





}