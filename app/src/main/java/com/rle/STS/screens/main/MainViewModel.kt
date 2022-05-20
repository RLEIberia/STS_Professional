package com.rle.STS.screens.main

import android.content.Context
import android.graphics.Insets.add
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rle.STS.data.DataOrException
import com.rle.STS.model.APIs.projects.ProjectsResponse
import com.rle.STS.model.BBDD.ChecklistsTable
import com.rle.STS.model.BBDD.ProjectsTable
import com.rle.STS.model.JSON.checklistStructure.ChecklistJSON
import com.rle.STS.repository.APIRepository
import com.rle.STS.repository.ChecklistRepository
import com.rle.STS.repository.DbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val checklistRepository: ChecklistRepository,
    private val apiRepository: APIRepository,
    private val dbRepository: DbRepository
) : ViewModel() {


    private val _projectsList = MutableStateFlow<List<ProjectsTable>>(emptyList())
    val projectsList = _projectsList.asStateFlow()


    init {
        viewModelScope.launch(Dispatchers.IO) {
            dbRepository.getProjects().distinctUntilChanged()
                .collect() { listOfProjects ->
                    if (listOfProjects.isNullOrEmpty()) {
                        Log.d("TAG", ":Empty list")
                    } else {
                        _projectsList.value = listOfProjects
                        Log.d("PROJECTS", _projectsList.toString())
                    }
                }

        }
    }


    /* TODO SACAR ESTO - PRUEBAS */

    private val _checklistJSONData: MutableStateFlow<ChecklistJSON> =
        MutableStateFlow(ChecklistJSON())
    val checklistData = _checklistJSONData.asStateFlow()

    fun extractChecklist(fileName: String, context: Context) =
        viewModelScope.launch {
            val jsonData = checklistRepository.getJson(
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

    //Cogemos respuesta de proyectos de la API
    fun apiGetProjects() {
        viewModelScope.launch(Dispatchers.IO) {
            _APIprojectResponse.value.loading = true
            Log.d("API_L", _APIprojectResponse.value.loading.toString())
            Log.d("API_D", _APIprojectResponse.value.data.toString())
            _APIprojectResponse.value = apiRepository.getProjects()
            if (_APIprojectResponse.value.data.toString()
                    .isNotEmpty() || _APIprojectResponse.value.e.toString().isNotEmpty()
            ) {
                _APIprojectResponse.value.loading = false
                Log.d("LOAD", _APIprojectResponse.value.loading.toString())
                Log.d("EX", _APIprojectResponse.value.e.toString())
            }
            Log.d("API_L", _APIprojectResponse.value.loading.toString())
            Log.d("API_E", _APIprojectResponse.value.e.toString())
            Log.d("API_D", _APIprojectResponse.value.data.toString())
        }
    }

    fun insertMultipleChecklists(checklists: ArrayList<ChecklistsTable>) {
        viewModelScope.launch { dbRepository.insertMultipleChecklists(checklists) }
    }

    fun insertMultipleProjects(projects: ArrayList<ProjectsTable>) {
        viewModelScope.launch { dbRepository.insertMultipleProjects(projects) }
    }

    fun insertChecklist(checklist: ChecklistsTable) {
        viewModelScope.launch { dbRepository.insertChecklist(checklist) }
    }

    fun insertProject(project: ProjectsTable) {
        viewModelScope.launch { dbRepository.insertProject(project) }
    }

    fun getProjectById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            dbRepository.getProjectById(id)
        }
    }

    //Get a project by multiple ids
    private val _multipleProjectsByIds: MutableStateFlow<List<ProjectsTable>> =
        MutableStateFlow(emptyList())
    val multipleProjectsByIds = _multipleProjectsByIds.asStateFlow()
    fun getMultipleProjectsByIds(ids: Array<Int>) {
        viewModelScope.launch {
            _multipleProjectsByIds.value = dbRepository.getMultipleProjectsByIds(ids)
        }
    }
}