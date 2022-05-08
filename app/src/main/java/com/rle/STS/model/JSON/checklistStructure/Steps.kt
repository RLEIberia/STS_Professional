package com.rle.STS.model.JSON.checklistStructure

import com.rle.STS.model.JSON.utils.Path

data class Steps(
    val position: Int,
    val idStep: String,
    val stepName: String,
    val numViews: Int,
    val path: ArrayList<Path>,
    val views: ArrayList<Views>
)
