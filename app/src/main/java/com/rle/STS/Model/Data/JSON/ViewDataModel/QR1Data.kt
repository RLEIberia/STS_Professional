package com.rle.STS.Model.Data.JSON.ViewDataModel

import com.rle.STS.Model.Data.JSON.ViewDataModel.Utils.ViewData

data class QR1Data(
    val text : String,
    val audio : String?
) : ViewData()
