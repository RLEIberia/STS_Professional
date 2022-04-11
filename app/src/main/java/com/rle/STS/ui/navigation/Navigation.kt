package com.rle.STS.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rle.STS.Screens.ProjectsChecklists
import com.rle.STS.ui.Screens.ChecklistScreens.CheckListStepScreen
import com.rle.STS.ui.Screens.MainMenu
import com.rle.STS.ui.Screens.StateScreen
import com.rle.STS.ui.theme.CheckListaApplicationTheme

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "main"
    ) {
        composable(route = "main") {
            CheckListaApplicationTheme {
                MainMenu(
                    onProjectsClick = {
                        navController.navigate(NavItem.PCList.createNavRoute(true))
                    },
                    onStateClick = {
                        navController.navigate(NavItem.State.route)
                    }
                )
            }
        }

        composable(NavItem.PCList) { backStackEntry ->    //Lista checklists
            CheckListaApplicationTheme {
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

                val CheckLists = ArrayList<String>()
                CheckLists.add("Checklist uno")
                CheckLists.add("Checklist dos")
                CheckLists.add("Checklist tres")
                CheckLists.add("Checklist cuatro")
                CheckLists.add("Checklist cinco")
                CheckLists.add("Checklist seis")
                CheckLists.add("Checklist siete")
                CheckLists.add("Checklist ocho")
                CheckLists.add("Checklist nueve")
                CheckLists.add("Checklist diez")
                CheckLists.add("Checklist once")
                CheckLists.add("Checklist doce")
                CheckLists.add("Checklist trece")
                CheckLists.add("Checklist catorce")
                CheckLists.add("Checklist quince")
                CheckLists.add("Checklist dieciseis")
                CheckLists.add("Checklist diecisiete")

                val projects = backStackEntry.arguments!!.getBoolean("isP")

                if (projects) {
                    ProjectsChecklists({
                        navController.navigate(NavItem.PCList.createNavRoute(false))
                    }, itemsList = projectsList, title = "", letter = "P")
                } else {
                    ProjectsChecklists({
                        navController.navigate(NavItem.View.createNavRoute("test"))
                    }, itemsList = CheckLists, title = "", letter = "C")
                }
            }
        }

        composable(NavItem.View) { backStackEntry ->
            CheckListaApplicationTheme {
                CheckListStepScreen()
            }
        }

        composable(NavItem.State) { backStackEntry ->
            CheckListaApplicationTheme {
                StateScreen()
            }
        }

    }
}


private fun NavGraphBuilder.composable(
    navItem: NavItem,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = navItem.route,
        arguments = navItem.args
    ) {
        content(it)
    }
}

private inline fun <reified T> NavBackStackEntry.findArg(arg: NavArg): T {
    val value = arguments?.get(arg.key)
    requireNotNull(value)
    return value as T
}