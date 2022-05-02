package com.rle.STS.Model.Data.JSON.ViewDataModel

import com.rle.STS.Model.Data.JSON.ViewDataModel.Utils.PathInfo
import com.rle.STS.Model.Data.JSON.ViewDataModel.Utils.ViewData

data class OP2Data(

    //Data
    val text: String,
    val audio: String?,
    val nOptions: Int,
    val options: ArrayList<String>,

    //Path
    val pathOptions: ArrayList<PathInfo>?

) : ViewData()
