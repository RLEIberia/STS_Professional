package com.rle.STS.items

sealed class NavDrawerItem(var route: String, var title: String) {
    object Home : NavDrawerItem("home", "Home")
    object Music : NavDrawerItem("music", "Music")
    object Movies : NavDrawerItem("movies", "Movies")
    object Books : NavDrawerItem("books", "Books")
    object Profile : NavDrawerItem("profile", "Profile")
    object Settings : NavDrawerItem("settings", "Settings")
}
