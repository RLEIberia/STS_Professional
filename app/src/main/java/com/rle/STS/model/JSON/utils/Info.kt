package com.rle.myapplication.model.checklistInfo

data class Info(
    val language: String,
    val checklistName: String,
    val checklistCode: String,
    val projectName: String,
    val projectCode: String,
    val version: Int,
    val createDate: Long,
    val modDate: Long,
    val designer: String,
    val manufacturer: String,
    val client: String
)