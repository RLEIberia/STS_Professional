package com.rle.STS.model.JSON.checklistStructure

data class Path(
    val actionCode: String,
    val message: String,
    val nextStep: ArrayList<Int>,
    //val view: Int
)
