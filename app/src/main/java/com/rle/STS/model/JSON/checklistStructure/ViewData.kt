package com.rle.STS.model.JSON.checklistStructure

import com.rle.STS.model.JSON.utils.FileInfo

data class ViewData(

    //reproduce text as audio
    val audio: String,

    //show text
    val text: ArrayList<String>, //Si hay 2 -> 0 título, 1 descripción

    //files
    val files: ArrayList<FileInfo>,

    //number introduction
    val type: String,
    val logic: String,
    val numbers: ArrayList<Double>,

    //Options
    val nOptions: Int,
    val options: ArrayList<String>,
    //qr cuenta como files

    //answer
    val blockUntilAnswer: Int,

    )