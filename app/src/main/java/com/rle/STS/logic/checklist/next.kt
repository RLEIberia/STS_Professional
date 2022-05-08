package com.rle.STS.logic.checklist

fun next(
    //TODO - Implementar ViewModel
) {

    var viewPosition: Int = 0
    var stepPosition: Int
    val maxViews = 10 //TODO - Viene de length del array

    if(viewPosition < maxViews-1) viewPosition++
    else nextStep()

}