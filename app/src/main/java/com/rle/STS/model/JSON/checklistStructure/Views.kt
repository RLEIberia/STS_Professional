package com.rle.STS.model.JSON.checklistStructure

data class Views(
    val idView: Int,
    val name: String,
    val viewOrder: Int,
    val viewType: String,
    val viewData: ViewData
)
