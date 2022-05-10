package com.rle.STS.items

sealed class NavDrawerItem(var route: String, var title: String) {
    object Configuracion : NavDrawerItem("configuracion", "Configuración")
    object Informacion : NavDrawerItem("informacion", "Información")
    object Volver : NavDrawerItem("volver", "Volver")
    object Salir : NavDrawerItem("salir", "Salir")
}
