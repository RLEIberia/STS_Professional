package com.rle.STS

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
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
import java.io.File
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

    private val _toastAction: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val toastAction = _toastAction.asStateFlow()

    private val _selectedChecklist: MutableStateFlow<ChecklistsTable> =
        MutableStateFlow(ChecklistsTable())
    val selectedChecklist = _selectedChecklist.asStateFlow()

    init {
        saveUserData(userData = UserData(1, "2d4b6637bfaa6224cd08f31a79ebf9ab"))

        try{
            getUserData(context = context)
            getUserById(context = context)
        } catch (e: Exception) {
            viewModelScope.launch(Dispatchers.IO) {
                //TODO Montar como LiveEvent
                _toastAction.value = true
                delay(100)
                _toastAction.value = false
            }
        }

    }

    //TODO guardar userData dependiendo del Login
    fun saveUserData(
        userData: UserData,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.saveUserData(
                userData = userData,
            )
        }
    }

    fun getUserData(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            _userSimple.value = dataStoreRepository.getUserData()
        }
    }

    fun getUserById(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            _userDbData.value = dbRepository.getUserById(dataStoreRepository.getUserData().userCode)
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

    fun createDirectories(
        context: Context
    ){
        viewModelScope.launch(Dispatchers.IO) {
            //Directory paths
            val audioInPath = context.filesDir.path + File.separator + "Audios" + File.separator + "In"
            val audioInDirectory = File(audioInPath)
            val videoInPath = context.filesDir.path + File.separator + "Videos" + File.separator + "In"
            val videoInDirectory = File(videoInPath)
            val imageInPath = context.filesDir.path + File.separator + "Images" + File.separator + "In"
            val imageInDirectory = File(imageInPath)
            val pdfInPath = context.filesDir.path + File.separator + "PDF" + File.separator + "In"
            val pdfInDirectory = File(pdfInPath)

            //Check if directory exists, if not create it
            if (!audioInDirectory.exists())
                audioInDirectory.mkdirs()
            if (!videoInDirectory.exists())
                videoInDirectory.mkdirs()
            if (!imageInDirectory.exists())
                imageInDirectory.mkdirs()
            if (!pdfInDirectory.exists())
                pdfInDirectory.mkdirs()

        }
    }

}