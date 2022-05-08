package com.rle.STS.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.rle.STS.screens.MainScreen
import androidx.navigation.compose.composable
import com.rle.STS.model.JSON.checklistStructure.Checklist
import com.rle.STS.screens.checklist.CheckListStepScreen
import com.rle.STS.screens.checklistSelect.ChecklistSelectScreen
import com.rle.STS.screens.projectSelect.ProjectSelectScreen


@Composable
fun STSNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = STSScreens.MainScreen.name
    ) {

        //TODO SplashScreen

        composable(STSScreens.MainScreen.name) {
            MainScreen(navController = navController)
        }

        composable(STSScreens.ProjectSelectScreen.name) {
            ProjectSelectScreen(navController = navController)
        }

        composable(STSScreens.ChecklistSelectScreen.name) {
            ChecklistSelectScreen(navController = navController)
        }

        composable(STSScreens.Checklist.name) {
            CheckListStepScreen(navController = navController)
        }


    }

}