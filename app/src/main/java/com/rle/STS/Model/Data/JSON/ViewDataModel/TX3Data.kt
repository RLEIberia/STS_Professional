package com.rle.STS.Model.Data.JSON.ViewDataModel

import com.rle.STS.Model.Data.JSON.ViewDataModel.Utils.ViewData

data class TX3Data(
    val title: String,
    val text: String,
    val audio: String?
) : ViewData()
