package com.rle.STS.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rle.STS.model.BBDD.ChecklistsTable
import com.rle.STS.model.BBDD.ProjectsTable
import com.rle.STS.repository.DbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChecklistSelectViewModel @Inject constructor(
    private val dbRepository: DbRepository
) : ViewModel() {

    private val _checklistList: MutableStateFlow<List<ChecklistsTable>> =
        MutableStateFlow(emptyList())
    val checklistList = _checklistList.asStateFlow()

    fun getChecklistsByProject(projectId: Int) {
        viewModelScope.launch(Dispatchers.IO){
            _checklistList.value =
                dbRepository.getMultipleChecklistsByProject(projectId)
        }
    }
}