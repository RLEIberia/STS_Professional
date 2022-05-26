package com.rle.STS.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.rle.STS.screens.MainScreen
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.rle.STS.ActivityViewModel
import com.rle.STS.screens.checklist.ChecklistScreen
import com.rle.STS.screens.checklist.ChecklistViewModel
import com.rle.STS.screens.checklistSelect.ChecklistSelectScreen
import com.rle.STS.screens.checklistSelect.ChecklistSelectViewModel
import com.rle.STS.screens.main.MainViewModel
import com.rle.STS.screens.projectSelect.ProjectSelectScreen
import com.rle.STS.screens.projectSelect.ProjectsViewModel
import com.rle.STS.screens.splash.SplashScreen
import com.rle.STS.screens.splash.SplashViewModel

@Composable
fun STSNavigation() {

    val navController = rememberNavController()
    val activityViewModel: ActivityViewModel = hiltViewModel()
    val splashViewModel: SplashViewModel = hiltViewModel()
    val projectsViewModel: ProjectsViewModel = hiltViewModel()
    val mainViewModel: MainViewModel = hiltViewModel()
    val checklistSelectViewModel: ChecklistSelectViewModel = hiltViewModel()
    val checklistViewModel: ChecklistViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = STSScreens.SplashScreen.name
    ) {

        composable(STSScreens.SplashScreen.name) {
            SplashScreen(
                navController = navController,
                splashViewModel = splashViewModel,
                activityViewModel = activityViewModel
            )
            //TODO FadeOut
        }

        composable(STSScreens.MainScreen.name) {
            MainScreen(
                navController = navController,
                activityViewModel = activityViewModel,
                mainViewModel = mainViewModel
            )
        }

        composable(STSScreens.ProjectSelectScreen.name) {
            ProjectSelectScreen(
                navController = navController,
                activityViewModel = activityViewModel,
                projectsViewModel = projectsViewModel
            )
        }

        composable(STSScreens.ChecklistSelectScreen.name) {
            ChecklistSelectScreen(
                navController = navController,
                activityViewModel = activityViewModel,
                checklistSelectViewModel = checklistSelectViewModel,
            )
        }

        composable(STSScreens.ChecklistScreen.name) {
            ChecklistScreen(
                navController = navController,
                checklistViewModel = checklistViewModel
            )
        }
    }
}