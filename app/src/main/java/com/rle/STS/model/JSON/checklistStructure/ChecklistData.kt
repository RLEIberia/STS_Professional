package com.rle.STS.model.JSON.checklistStructure

data class ChecklistData(
    val numSteps: Int,
    val steps: ArrayList<Steps>
)