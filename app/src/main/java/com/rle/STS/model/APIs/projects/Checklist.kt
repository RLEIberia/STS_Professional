package com.rle.STS.model.APIs.projects

data class Checklist (
    val id: Int,
    val name: String,
    val version: Int,
    val description: String,
    val project_id: Int,
    val created_at: Long,
    val updated_at: Long
)
