package com.rle.STS.items

sealed class NavDrawerItemChecklist(var route: String, var title: String) {
    object Paso_anterior : NavDrawerItemChecklist("configuracion", "Paso anterior")
    object Paso_siguiente : NavDrawerItemChecklist("informacion", "Paso siguiente")
    object Volver : NavDrawerItemChecklist("volver", "Volver")
    object Inicio : NavDrawerItemChecklist("salir", "Salir")
}
