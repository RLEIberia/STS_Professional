package com.rle.STS.Model.Data.JSON.ViewDataModel.Utils

data class PathInfo(
    val action: String,
    val message: String? ="",
    val step: Int,
    val view: Int
)
