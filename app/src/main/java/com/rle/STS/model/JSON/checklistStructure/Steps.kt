package com.rle.STS.model.JSON.checklistStructure

data class Steps(
    val position: Int,
    val idStep: Int,
    val stepName: String,
    val numViews: Int,
    val path: ArrayList<Path>,
    val views: ArrayList<Views>
)
