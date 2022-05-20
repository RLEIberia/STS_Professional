package com.rle.STS.model.BBDD

import androidx.room.*
import com.rle.STS.model.APIs.projects.Project

@Entity(
    tableName = "projects_table",
    //indices = [Index(value = ["id"], unique = true)]
)
data class ProjectsTable(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "version")
    val version: Int,

    @ColumnInfo(name = "activated")
    val activated: Int,

    @ColumnInfo(name = "created_at")
    val created_at: Long,

    @ColumnInfo(name = "updated_at")
    val updated_at: Long,

    @ColumnInfo(name = "state")
    val state: Int,

)