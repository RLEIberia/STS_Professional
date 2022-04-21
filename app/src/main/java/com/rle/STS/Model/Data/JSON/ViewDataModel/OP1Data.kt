package com.rle.STS.Model.Data.JSON.ViewDataModel

import com.rle.STS.Model.Data.JSON.ViewDataModel.Utils.PathInfo
import com.rle.STS.Model.Data.JSON.ViewDataModel.Utils.ViewData

data class OP1Data(

    //Data
    val text: String,
    val audio: String?,

    //Path
    val pathOk: PathInfo?, //Ok
    val pathKo: PathInfo?, //Not Ok
    val pathNk: PathInfo?  //Do Not Know, No Answer

) : ViewData()
