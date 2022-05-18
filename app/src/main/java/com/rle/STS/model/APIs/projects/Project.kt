package com.rle.STS.model.APIs.projects

import com.rle.STS.model.BBDD.ProjectsTable

data class Project(
    val id: Int,
    val name: String,
    val description: String,
    val version: Int,
    val activated: Int,
    val created_at: Long,
    val updated_at: Long,
    val checklists: ArrayList<Checklist>
)
