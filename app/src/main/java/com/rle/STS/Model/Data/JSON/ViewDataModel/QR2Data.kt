package com.rle.STS.Model.Data.JSON.ViewDataModel

import com.rle.STS.Model.Data.JSON.ViewDataModel.Utils.PathInfo
import com.rle.STS.Model.Data.JSON.ViewDataModel.Utils.QRInfo
import com.rle.STS.Model.Data.JSON.ViewDataModel.Utils.ViewData

data class QR2Data(

    //Data
    val text : String,
    val audio : String?,
    val nQR : Int, //Número de QRs
    val validateQR : ArrayList<QRInfo>, //Listado de QRs

    //Path
    val pathOptions : ArrayList<PathInfo>?, //Camino según QR
    val pathNot : PathInfo?  //Camino si el QR no existe en el listado

) : ViewData()
