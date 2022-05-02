package com.rle.STS.Model.Data.JSON.ViewDataModel

import com.rle.STS.Model.Data.JSON.ViewDataModel.Utils.ViewData

data class NM1Data(
    val text: String,
    val audio: String?,
    val type: String,
    val rangeDownOk: Double,
    val rangeUpOk: Double
) : ViewData()