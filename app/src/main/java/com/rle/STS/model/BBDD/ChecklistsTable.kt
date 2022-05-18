package com.rle.STS.model.BBDD

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import com.rle.STS.model.APIs.projects.Project

@Entity(
    tableName = "checklists_table",
    foreignKeys = [ForeignKey(
        entity = ProjectsTable::class,
        parentColumns = ["id"],
        childColumns = ["project_id"],
        onDelete = CASCADE
    )]
)
data class ChecklistsTable(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "checklist_id")
    val checklist_id: Int,

    @ColumnInfo(name = "project_id")
    val project_id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "created_at")
    val created_at: Long,

    @ColumnInfo(name = "updated_at")
    val updated_at: Long,

    @ColumnInfo(name = "version")
    val version: Int,

    @ColumnInfo(name = "activated")
    val activated: Int,

    @ColumnInfo(name = "executions_counter")
    val executions_counter: Int,

    @ColumnInfo(name = "json")
    val json: String,

    @ColumnInfo(name = "state")
    val state: Int //0 - Not ready, 1 - Downloading, 2 - Ready, 3 - Delete, 4 - Delete after ending, 99 - Internal

)
