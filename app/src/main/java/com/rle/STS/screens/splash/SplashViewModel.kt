package com.rle.STS.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.rle.STS.navigation.STSScreens
import com.rle.STS.repository.DbRepository
import com.rle.STS.utils.TableInserts
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val dbRepository: DbRepository
) : ViewModel(){

    //Lo usamos para hacer añadidos extra en caso de no poder desde plataforma o que haya cualquier problema

    init {
        viewModelScope.launch(Dispatchers.IO){
            dbRepository.insertUser(TableInserts.user1)
            dbRepository.insertUser(TableInserts.user2)

            //Si la base de datos no está creada no va bien
            //dbRepository.insertChecklist(TableInserts.checklistExtra1)

            dbRepository.insertProject(TableInserts.projectExtra1)
            dbRepository.insertProject(TableInserts.projectExtra2)
            dbRepository.insertProject(TableInserts.projectExtra3)
            dbRepository.insertProject(TableInserts.projectExtra4)
            dbRepository.insertProject(TableInserts.projectExtra5)
            dbRepository.insertProject(TableInserts.projectExtra6)


        }

    }

    fun navigate(navController: NavController){
        viewModelScope.launch(Dispatchers.IO) {
            delay(2000L)
            navController.navigate(STSScreens.MainScreen.name){
            }
        }
    }

}