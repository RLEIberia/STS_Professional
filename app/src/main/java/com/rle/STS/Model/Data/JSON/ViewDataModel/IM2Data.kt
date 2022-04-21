package com.rle.STS.Model.Data.JSON.ViewDataModel

import com.rle.STS.Model.Data.JSON.ViewDataModel.Utils.ViewData

data class IM2Data(
    val audio: String?,
    val files: ArrayList<String>
) : ViewData()
