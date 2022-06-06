package com.rle.STS.logic.checklist

import com.rle.STS.model.JSON.checklistStructure.ChecklistJSON
import com.rle.STS.model.extra.ChecklistPosition

fun next(
    currentPosition: ChecklistPosition,
    checklist: ChecklistJSON,
    choice: Int
): ChecklistPosition {


    var viewPosition = currentPosition.view
    var stepPosition = currentPosition.step
    val maxViews = 10 //TODO - Viene de length del array

    if (viewPosition + 1 < checklist.checklistData!!.steps[currentPosition.step].views.size) viewPosition++
    else nextStep()

    return currentPosition

}