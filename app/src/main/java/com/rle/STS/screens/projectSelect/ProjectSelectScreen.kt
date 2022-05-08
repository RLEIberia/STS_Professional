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
    projectsList.add("Proyecto ocho")
    projectsList.add("Proyecto nueve")
    projectsList.add("Proyecto diez")
    projectsList.add("Proyecto once")
    projectsList.add("Proyecto doce")
    projectsList.add("Proyecto trece")
    projectsList.add("Proyecto catorce")
    projectsList.add("Proyecto quince")
    projectsList.add("Proyecto dieciseis")
    projectsList.add("Proyecto diecisiete")

    ListScreen(itemsList = projectsList, letter = "P", title = "", navController = navController)

}