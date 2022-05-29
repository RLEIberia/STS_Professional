package com.rle.STS.screens.scanner

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rle.STS.model.BBDD.ChecklistsTable
import com.rle.STS.model.BBDD.FilesInTable
import com.rle.STS.model.BBDD.ProjectsTable
import com.rle.STS.repository.DbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScannerViewModel @Inject constructor(
    private val dbRepository: DbRepository
): ViewModel() {

    private val _scannedFile:  MutableStateFlow<FilesInTable> = MutableStateFlow(FilesInTable())
    val scannedFile = _scannedFile.asStateFlow()

    private val _scannedChecklist: MutableStateFlow<ChecklistsTable> = MutableStateFlow(ChecklistsTable())
    val scannedChecklist = _scannedChecklist.asStateFlow()

    private val _scannedProject: MutableStateFlow<ProjectsTable> = MutableStateFlow(ProjectsTable())
    val scannedProject = _scannedProject.asStateFlow()

    fun getFileById(
        fileId: Int,
    ){
        viewModelScope.launch(Dispatchers.IO) {
            _scannedFile.value = dbRepository.getFileById(fileId)
        }
    }

    fun getChecklistById(
        checklistId: Int
    ){
        viewModelScope.launch(Dispatchers.IO) {
            _scannedChecklist.value = dbRepository.getChecklistById(id = checklistId)
        }
    }

    fun getProjectById(
        projectId: Int
    ){
        viewModelScope.launch(Dispatchers.IO) {
            _scannedProject.value = dbRepository.getProjectById(id = projectId)
        }

    }

}