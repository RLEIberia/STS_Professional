package com.rle.STS

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rle.STS.model.BBDD.ChecklistsTable
import com.rle.STS.model.BBDD.ProjectsTable
import com.rle.STS.model.BBDD.UsersTable
import com.rle.STS.model.DataStore.UserData
import com.rle.STS.repository.DataStoreRepository
import com.rle.STS.repository.DbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
    private val dbRepository: DbRepository
) : ViewModel() {

    private val _userSimple: MutableStateFlow<UserData> = MutableStateFlow(UserData())
    val userSimple = _userSimple.asStateFlow()

    private val _userDbData: MutableStateFlow<UsersTable> =
        MutableStateFlow(UsersTable(-1, "", "", "", "", checklists_id = ""))
    val userDbData = _userDbData.asStateFlow()

    private val _selectedProject: MutableStateFlow<ProjectsTable> = MutableStateFlow(ProjectsTable())
    val selectedProject = _selectedProject.asStateFlow()

    private val _selectedChecklist: MutableStateFlow<ChecklistsTable> = MutableStateFlow(
        ChecklistsTable()
    )

    init {
        getUserData()
        getUserById()
    }

    //TODO guardar userData dependiendo del Login
    fun saveUserData(userData: UserData) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.saveUserData(
                userData = userData
            )
        }
    }

    fun getUserData() {
        viewModelScope.launch(Dispatchers.IO) {
            _userSimple.value = dataStoreRepository.getUserData()
        }
    }

    fun getUserById() {
        viewModelScope.launch(Dispatchers.IO) {
            _userDbData.value = dbRepository.getUserById(dataStoreRepository.getUserData().userCode)
        }
    }

    fun getProjectById(id: Int) {
        Log.d("GETP", "getProjectByID launched")
        viewModelScope.launch(Dispatchers.IO) {
            _selectedProject.value = dbRepository.getProjectById(id = id)
            Log.d("_SelectedProject", _selectedProject.value.toString())
            Log.d("SelectedProject", selectedProject.value.toString())
        }
    }

    fun getChecklistById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _selectedChecklist.value = dbRepository.getChecklistById(id = id)
        }
    }

}