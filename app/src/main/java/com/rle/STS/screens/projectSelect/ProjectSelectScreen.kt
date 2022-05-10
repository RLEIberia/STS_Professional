package com.rle.STS.screens.projectSelect

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.rle.STS.screens.ListScreen

@Composable
fun ProjectSelectScreen(
    navController: NavController,
    //projectViewModel:
){

    //TODO Cambiar origen de la lista
    val projectsList = ArrayList<String>()
    projectsList.add("Proyecto uno")
    projectsList.add("Proyecto dos")
    projectsList.add("Proyecto tres")
    projectsList.add("Proyecto cuatro")
    projectsList.add("Proyecto cinco")
    projectsList.add("Proyecto seis")
    projectsList.add("Proyecto siete")


    ListScreen(itemsList = projectsList, letter = "P", title = "", navController = navController)

}