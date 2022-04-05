package com.rle.STS.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class NavItem(
    val baseRoute: String,
    val navArgs: List<NavArg> = emptyList()
) {
    val route = run {
        // baseroute/{arg1}/{arg2}
        val argKeys = navArgs.map{ "{${it.key}}" }
        listOf(baseRoute)
            .plus(argKeys)
            .joinToString("/")
    }

    val args = navArgs.map{
        navArgument(it.key) {type = it.navType }
    }

    object Main: NavItem("main",listOf(NavArg.IsProjects)){ //Lista checklists
        fun createNavRoute(isP: Boolean) = "$baseRoute/$isP"
    }
    object View: NavItem("view", listOf(NavArg.Text)){
        fun createNavRoute(text: String) = "$baseRoute/$text"
    }
    object Text: NavItem("text")

}

enum class NavArg(val key: String, val navType: NavType<*>) {
    Text("text", NavType.StringType),
    IsProjects("main", NavType.BoolType)
}