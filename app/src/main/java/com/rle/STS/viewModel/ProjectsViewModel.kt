package com.rle.STS.viewModel

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
    private val _index: MutableStateFlow<Int> = MutableStateFlow(0)
    val index = _index.asStateFlow()

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
    fun indexMove(value: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _index.value = _index.value+value
        }
    }

}