package com.rle.STS.screens.projects

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rle.STS.model.BBDD.ProjectsTable
import com.rle.STS.repository.DatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProjectsViewModel @Inject constructor(private val DatabaseRepository: DatabaseRepository): ViewModel() {

    private val _projectsList = MutableStateFlow<List<ProjectsTable>>(emptyList())
    val projectsList = _projectsList.asStateFlow()

    init{
        viewModelScope.launch(Dispatchers.IO) {
            DatabaseRepository.getProjects().distinctUntilChanged()
                .collect() { listOfProjects ->
                    if (listOfProjects.isNullOrEmpty()) {
                        Log.d("TAG", ":Empty list")
                    } else {
                        _projectsList.value = listOfProjects
                    }

                }
        }
    }

}