package com.rle.STS.Model.Data.JSON.ViewDataModel

import com.rle.STS.Model.Data.JSON.ViewDataModel.Utils.QRInfo
import com.rle.STS.Model.Data.JSON.ViewDataModel.Utils.ViewData

data class QR3Data(

    val text : String,
    val audio : String?,
    val nQR : Int, //Número de QRs
    val validateQR : ArrayList<QRInfo>, //Listado de QRs

) : ViewData()
