package com.rle.STS.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.rle.STS.screens.MainScreen
import androidx.navigation.compose.composable
import com.rle.STS.viewModel.ActivityViewModel
import com.rle.STS.screens.StateScreen
import com.rle.STS.screens.checklist.ChecklistScreen
import com.rle.STS.viewModel.ChecklistViewModel
import com.rle.STS.screens.checklistSelect.ChecklistSelectScreen
import com.rle.STS.viewModel.ChecklistSelectViewModel
import com.rle.STS.screens.documentSelect.DocumentSelectScreen
import com.rle.STS.viewModel.DocumentSelectViewModel
import com.rle.STS.viewModel.MainViewModel
import com.rle.STS.screens.projectSelect.ProjectSelectScreen
import com.rle.STS.viewModel.ProjectsViewModel
import com.rle.STS.viewModel.ScannerViewModel
import com.rle.STS.screens.splash.SplashScreen
import com.rle.STS.viewModel.SplashViewModel
import com.rle.STS.viewModel.StateViewModel

@Composable
fun STSNavigation() {

    val navController = rememberNavController()
    val activityViewModel: ActivityViewModel = hiltViewModel()
    val splashViewModel: SplashViewModel = hiltViewModel()
    val projectsViewModel: ProjectsViewModel = hiltViewModel()
    val mainViewModel: MainViewModel = hiltViewModel()
    val checklistSelectViewModel: ChecklistSelectViewModel = hiltViewModel()
    val checklistViewModel: ChecklistViewModel = hiltViewModel()
    val scannerViewModel: ScannerViewModel = hiltViewModel()
    val documentSelectViewModel: DocumentSelectViewModel = hiltViewModel()
    val stateViewModel: StateViewModel = hiltViewModel()

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
        }

        composable(STSScreens.MainScreen.name) {
            MainScreen(
                navController = navController,
                activityViewModel = activityViewModel,
                mainViewModel = mainViewModel,
                scannerViewModel = scannerViewModel
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
                checklistViewModel = checklistViewModel,
                activityViewModel = activityViewModel
            )
        }

        composable(STSScreens.DocumentSelectScreen.name) {
            DocumentSelectScreen(
                navController = navController,
                documentSelectViewModel = documentSelectViewModel
            )
        }

        composable(STSScreens.StateScreen.name){
            StateScreen(
                navController = navController,
                activityViewModel = activityViewModel,
                stateViewModel = stateViewModel,
                checklistViewModel = checklistViewModel
            )
        }

    }
}