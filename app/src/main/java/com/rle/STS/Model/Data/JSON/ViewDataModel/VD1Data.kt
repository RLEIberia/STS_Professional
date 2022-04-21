package com.rle.STS.Model.Data.JSON.ViewDataModel

import com.rle.STS.Model.Data.JSON.ViewDataModel.Utils.FileInfo
import com.rle.STS.Model.Data.JSON.ViewDataModel.Utils.ViewData

data class VD1Data(
    val text : String?,
    val audio : String?,
    val files : ArrayList<FileInfo>
) : ViewData()
