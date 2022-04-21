package com.rle.STS.Model.Data.JSON.ViewDataModel

import com.rle.STS.Model.Data.JSON.ViewDataModel.Utils.PathInfo
import com.rle.STS.Model.Data.JSON.ViewDataModel.Utils.ViewData

data class NM2Data(

    //Data
    val text: String,
    val audio: String?,
    val type: String,
    val rangeDownOk: Double?,
    val rangeUpOk: Double?,

    //Path
    val pathOk: PathInfo?,
    val pathDown: PathInfo?,
    val pathUp: PathInfo?

) : ViewData()