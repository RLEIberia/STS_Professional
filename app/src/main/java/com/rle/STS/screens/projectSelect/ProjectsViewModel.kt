package com.rle.STS.screens.projectSelect

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rle.STS.model.BBDD.ProjectsTable
import com.rle.STS.model.BBDD.UsersTable
import com.rle.STS.repository.DbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProjectsViewModel @Inject constructor(
    private val dbRepository: DbRepository,
) : ViewModel() {

    private val _userProjects: MutableStateFlow<List<ProjectsTable>> =
        MutableStateFlow(emptyList())
    val userProjects = _userProjects.asStateFlow()


//    fun getMultipleProjectsByIds(ids: Array<Int>) {
//        viewModelScope.launch {
//            _userProjects.value = dbRepository.getMultipleProjectsByIds(ids)
//        }
//    }

    fun getUserProjects(user: UsersTable) {
        viewModelScope.launch(Dispatchers.IO) {
            _userProjects.value =
                dbRepository.getMultipleProjectsByIds(
                    user.projects_id.split(",").map { it.toInt() }.toTypedArray()
                )
        }
    }

}