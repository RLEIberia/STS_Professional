package com.rle.STS.screens.splash

import android.content.Context
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.preferences.protobuf.Parser
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.rle.STS.model.DataStore.UserData
import com.rle.STS.navigation.STSScreens
import com.rle.STS.repository.APIRepository
import com.rle.STS.repository.ChecklistRepository
import com.rle.STS.repository.DataStoreRepository
import com.rle.STS.repository.DbRepository
import com.rle.STS.utils.TableInserts
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val dbRepository: DbRepository,
    private val dataStoreRepository: DataStoreRepository,
    private val checklistRepository: ChecklistRepository,
    private val apiRepository: APIRepository
) : ViewModel() {

    //Lo usamos para hacer añadidos extra en caso de no poder desde plataforma o que haya cualquier problema

    init {
        viewModelScope.launch(Dispatchers.IO) {
            dbRepository.insertUser(TableInserts.user1)
            dbRepository.insertUser(TableInserts.user2)

            //Si la base de datos no está creada no va bien

            saveUserData(userData = UserData(1, "2d4b6637bfaa6224cd08f31a79ebf9ab"))

            dbRepository.insertProject(TableInserts.projectExtra1)
            dbRepository.insertProject(TableInserts.projectExtra2)
            dbRepository.insertProject(TableInserts.projectExtra3)
            dbRepository.insertProject(TableInserts.projectExtra4)
            dbRepository.insertProject(TableInserts.projectExtra5)
            dbRepository.insertProject(TableInserts.projectExtra6)

            try{
                dbRepository.insertChecklist(TableInserts.checklistExtra1)
            } catch (e: Exception){
                Log.d("EX", ": $e")
            }
            dbRepository.insertFileIn(TableInserts.fileExtra1)
            dbRepository.insertFileIn(TableInserts.fileExtra2)
            dbRepository.insertFileIn(TableInserts.fileExtra3)
            dbRepository.insertFileIn(TableInserts.fileExtra4)
            dbRepository.insertFileIn(TableInserts.fileExtra5)
        }

    }

    fun navigate(navController: NavController) {
        viewModelScope.launch(Dispatchers.IO) {
            delay(2000L)
            navController.navigate(STSScreens.MainScreen.name) {
            }
        }
    }

    fun saveUserData(
        userData: UserData,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.saveUserData(
                userData = userData,
            )
        }
    }

    //TODO Cambiar de sitio
//    fun sendResult(
//        context: Context,
//        fileName: String
//    ) {
//        viewModelScope.launch(Dispatchers.IO) {
//
//            if (apiRepository.checkForInternet(context = context)) {
//
//                val jsonString = checklistRepository.getJson(context = context, fileName = fileName)
//                if (!jsonString!!.isNullOrEmpty()) {
//                    val jsonTest= JsonObject()
//                    val testPost = testPost("testPost")
////                    Log.d("JSON_RESULT", jsonResult.toString())
//                    apiRepository.uploadExecution(testPost)
//
//                } else {
//                    Log.d("RESULT", "Result file doesn't exist")
//                }
//            } else {
//                Log.d("INTERNET", "No internet conenection")
//            }
//
//        }
//    }

}