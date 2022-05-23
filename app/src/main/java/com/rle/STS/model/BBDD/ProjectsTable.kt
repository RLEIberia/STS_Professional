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
    val id: Int = -1,

    @ColumnInfo(name = "name")
    val name: String = "",

    @ColumnInfo(name = "description")
    val description: String = "",

    @ColumnInfo(name = "version")
    val version: Int = -1,

    @ColumnInfo(name = "activated")
    val activated: Int = -1,

    @ColumnInfo(name = "created_at")
    val created_at: Long = -1,

    @ColumnInfo(name = "updated_at")
    val updated_at: Long = -1,

    @ColumnInfo(name = "state")
    val state: Int = -1

)