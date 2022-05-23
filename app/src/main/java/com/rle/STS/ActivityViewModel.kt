package com.rle.STS

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rle.STS.model.BBDD.ChecklistsTable
import com.rle.STS.model.BBDD.ProjectsTable
import com.rle.STS.model.BBDD.UsersTable
import com.rle.STS.model.DataStore.UserData
import com.rle.STS.repository.DataStoreRepository
import com.rle.STS.repository.DbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
    private val dbRepository: DbRepository,
    @ApplicationContext context: Context
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
        saveUserData(userData = UserData(), context = context)
        getUserData(context = context)
        getUserById(context = context)
    }

    //TODO guardar userData dependiendo del Login
    fun saveUserData(
        userData: UserData,
        context: Context
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.saveUserData(
                userData = userData,
                context = context
            )
        }
    }

    fun getUserData(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            _userSimple.value = dataStoreRepository.getUserData(context = context)
        }
    }

    fun getUserById(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            _userDbData.value = dbRepository.getUserById(dataStoreRepository.getUserData(context = context).userCode)
        }
    }

    fun getProjectById(id: Int) {
        Log.d("GETP", "getProjectByID launched")
        viewModelScope.launch(Dispatchers.IO) {
            _selectedProject.value = dbRepository.getProjectById(id = id)
        }
    }

    fun getChecklistById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _selectedChecklist.value = dbRepository.getChecklistById(id = id)
        }
    }

}