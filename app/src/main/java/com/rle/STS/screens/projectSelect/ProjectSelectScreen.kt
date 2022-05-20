package com.rle.STS.screens.projectSelect

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rle.STS.ActivityViewModel
import com.rle.STS.screens.ListScreen

@Composable
fun ProjectSelectScreen(
    navController: NavController,
    projectsViewModel: ProjectsViewModel = hiltViewModel(),
    activityViewModel: ActivityViewModel = hiltViewModel()
){
    val userDbData = activityViewModel.userDbData.collectAsState().value
    val projectsList = projectsViewModel.userProjects.collectAsState().value

    //TODO Cambiar origen de la lista
    val dummyProjectList = ArrayList<String>()
    dummyProjectList.add("Proyecto uno")
    dummyProjectList.add("Proyecto dos")
    dummyProjectList.add("Proyecto tres")
    dummyProjectList.add("Proyecto cuatro")
    dummyProjectList.add("Proyecto cinco")
    dummyProjectList.add("Proyecto seis")
    dummyProjectList.add("Proyecto siete")

    if(userDbData.id!=-1){
        projectsViewModel.getUserProjects(userDbData)
    }

    if(projectsList.isNotEmpty()){
        ListScreen(itemsList = dummyProjectList, letter = "P", title = "", navController = navController)
    }



}