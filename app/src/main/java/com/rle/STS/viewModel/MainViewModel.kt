package com.rle.STS.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rle.STS.data.DataOrException
import com.rle.STS.model.APIs.projects.ProjectsResponse
import com.rle.STS.model.BBDD.ChecklistsTable
import com.rle.STS.model.BBDD.ProjectsTable
import com.rle.STS.repository.APIRepository
import com.rle.STS.repository.ChecklistRepository
import com.rle.STS.repository.DbRepository
import com.rle.STS.utils.converters.toChecklistsTable
import com.rle.STS.utils.converters.toProjectsTable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val checklistRepository: ChecklistRepository,
    private val apiRepository: APIRepository,
    private val dbRepository: DbRepository
) : ViewModel() {

    private val _APIprojectResponse: MutableStateFlow<DataOrException<ProjectsResponse, Boolean, Exception>> =
        MutableStateFlow(DataOrException(null, true, null))

    val APIprojectResponse = _APIprojectResponse.asStateFlow()

    //Cogemos respuesta de proyectos de la API
    fun apiGetProjects(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {

            if (apiRepository.checkForInternet(context = context)) {

                Log.d("INTERNET", "Internet connection detected")

                _APIprojectResponse.value.loading = true
                _APIprojectResponse.value = apiRepository.getProjects()
                if (_APIprojectResponse.value.data.toString()
                        .isNotEmpty() || _APIprojectResponse.value.e.toString().isNotEmpty()
                ) {
                    _APIprojectResponse.value.loading = false
                    if (_APIprojectResponse.value.data != null) {
                        Log.d("API", _APIprojectResponse.value.data.toString())
                        dbRepository.insertMultipleProjects(toProjectsTable(_APIprojectResponse.value))
                        dbRepository.insertMultipleChecklists(toChecklistsTable(_APIprojectResponse.value))
                    }
                }

            } else {
                Log.d("INTERNET", "No internet conenection")
            }
        }
    }

    fun insertMultipleChecklists(apiProjectsResponse: DataOrException<ProjectsResponse, Boolean, Exception>) {
        viewModelScope.launch(Dispatchers.IO) {
            dbRepository.insertMultipleChecklists(toChecklistsTable(apiProjectsResponse))
        }
    }

    fun insertMultipleProjects(apiProjectsResponse: DataOrException<ProjectsResponse, Boolean, Exception>) {
        viewModelScope.launch(Dispatchers.IO) {

            dbRepository.insertMultipleProjects(toProjectsTable(apiProjectsResponse))
        }
    }

    fun insertChecklist(checklist: ChecklistsTable) {
        viewModelScope.launch(Dispatchers.IO) { dbRepository.insertChecklist(checklist) }
    }

    fun insertProject(project: ProjectsTable) {
        viewModelScope.launch(Dispatchers.IO) { dbRepository.insertProject(project) }
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
        viewModelScope.launch(Dispatchers.IO) {
            _multipleProjectsByIds.value = dbRepository.getMultipleProjectsByIds(ids)
        }
    }
}