package com.rle.STS.ui.navigation

import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rle.STS.Screens.ProjectsChecklists
import com.rle.STS.getActivity
import com.rle.STS.ui.Screens.ChecklistScreens.CheckListStepScreen
import com.rle.STS.ui.Screens.ChecklistScreens.Data.ImageScreen
import com.rle.STS.ui.Screens.ChecklistScreens.Data.TextScreen
import com.rle.STS.ui.Screens.PhotoInspect
import com.rle.STS.ui.theme.CheckListaApplicationTheme

@Composable
fun Navigation() {
    val navController = rememberNavController()

    val InitialARG_ID = "true"

    val activity = LocalContext.current.getActivity()

    NavHost(
        navController = navController,
        startDestination = "main/{$InitialARG_ID}"
    ) {
        composable(     //Si Start destination tiene argumentos debe ser una ruta con los argumentos ya definidos en ella
            route = "main/{$InitialARG_ID}",
            arguments = listOf(navArgument(InitialARG_ID) {
                type = NavType.BoolType; defaultValue = true
            }),
        ) {
            CheckListaApplicationTheme {
                val itemsList = ArrayList<String>()
                itemsList.add("Proyecto uno")
                itemsList.add("Proyecto dos")
                itemsList.add("Proyecto tres")
                itemsList.add("Proyecto cuatro")
                itemsList.add("Proyecto cinco")
                itemsList.add("Proyecto seis")
                itemsList.add("Proyecto siete")
                itemsList.add("Proyecto ocho")
                itemsList.add("Proyecto nueve")
                itemsList.add("Proyecto diez")
                itemsList.add("Proyecto once")
                itemsList.add("Proyecto doce")
                itemsList.add("Proyecto trece")
                itemsList.add("Proyecto catorce")
                itemsList.add("Proyecto quince")
                itemsList.add("Proyecto dieciseis")
                itemsList.add("Proyecto diecisiete")
                ProjectsChecklists({

                    navController.navigate(NavItem.Main.createNavRoute(false))

                    Log.d("TEST", (activity==null).toString())


                }, itemsList = itemsList, title = "", letter = "P")
            }
        }


        composable(NavItem.Main) { backStackEntry ->    //Lista checklists
            CheckListaApplicationTheme {

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
                ProjectsChecklists({
                    navController.navigate(NavItem.View.createNavRoute("test"))

                    //navController.navigate(NavItem.Inspect.createNavRoute(text))
                }, itemsList = CheckLists, title = "", letter = "C")
            }
        }
        composable(NavItem.View) { backStackEntry ->
            CheckListaApplicationTheme {
                CheckListStepScreen()
                Modifier.clearAndSetSemantics {  }

            }
        }

        composable(NavItem.Text) { backStackEntry ->
            CheckListaApplicationTheme {
                TextScreen()
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