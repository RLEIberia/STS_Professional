package com.rle.STS.model.JSON.checklistStructure

data class Path(
    val action: String,
    val message: String? ="",
    val step: ArrayList<Int>,
    //val view: Int
)
