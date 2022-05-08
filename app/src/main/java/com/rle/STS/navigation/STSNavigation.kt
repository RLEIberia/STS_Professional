package com.rle.STS.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.rle.STS.screens.MainScreen
import androidx.navigation.compose.composable
import com.rle.STS.screens.checklist.ChecklistScreen
import com.rle.STS.screens.checklistSelect.ChecklistSelectScreen
import com.rle.STS.screens.projectSelect.ProjectSelectScreen
import com.rle.STS.screens.splash.SplashScreen

@Composable
fun STSNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = STSScreens.SplashScreen.name
    ) {

        composable(STSScreens.SplashScreen.name) {
            SplashScreen(navController = navController)
            //TODO FadeOut
        }

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
            ChecklistScreen(navController = navController)
        }

    }
}