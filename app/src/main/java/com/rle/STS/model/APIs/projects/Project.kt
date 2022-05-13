package com.rle.STS.model.APIs.projects

data class Project(
    val id: Int,
    val name: String,
    val version: Int,
    val description: String,
    val created_at: Long,
    val updated_at: Long,
    val checklists: ArrayList<Checklist>
)


