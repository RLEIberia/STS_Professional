package com.rle.STS.screens.checklistSelect

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.rle.STS.screens.ListScreen

@Composable
fun ChecklistSelectScreen(
    navController: NavController,
    //TODO viewModel
) {

    val ckList = ArrayList<String>()
    ckList.add("Checklist uno")
    ckList.add("Checklist dos")
    ckList.add("Checklist tres")
    ckList.add("Checklist cuatro")
    ckList.add("Checklist cinco")
    ckList.add("Checklist seis")
    ckList.add("Checklist siete")
    ckList.add("Checklist ocho")
    ckList.add("Checklist nueve")
    ckList.add("Checklist diez")
    ckList.add("Checklist once")
    ckList.add("Checklist doce")
    ckList.add("Checklist trece")
    ckList.add("Checklist catorce")
    ckList.add("Checklist quince")
    ckList.add("Checklist dieciseis")
    ckList.add("Checklist diecisiete")

    ListScreen(itemsList = ckList, letter = "C", title = "", navController = navController)


}