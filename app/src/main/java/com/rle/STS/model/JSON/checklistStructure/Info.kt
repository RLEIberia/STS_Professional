package com.rle.STS.model.JSON.checklistStructure

data class Info(
    val language: String,
    val checklistName: String,
    val checklistCode: String,
    val projectName: String,
    val projectCode: String,
    val version: Int,
    val created_at: Long,
    val updated_at: Long,
    val designer: String,
    val manufacturer: String,
    val client: String
)