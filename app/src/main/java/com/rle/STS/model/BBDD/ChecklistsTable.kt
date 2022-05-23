package com.rle.STS.model.BBDD

import androidx.annotation.NonNull
import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import androidx.room.util.TableInfo
import com.rle.STS.model.APIs.projects.Project

@Entity(
    //indices = [Index(value = ["id"], unique = true)],
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
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int = -1,

    @ColumnInfo(name = "checklist_id")
    val checklist_id: Int = -1,

    @ColumnInfo(name = "project_id")
    val project_id: Int = -1,

    @ColumnInfo(name = "name")
    val name: String = "",

    @ColumnInfo(name = "description")
    val description: String = "",

    @ColumnInfo(name = "created_at")
    val created_at: Long = -1,

    @ColumnInfo(name = "updated_at")
    val updated_at: Long = -1,

    @ColumnInfo(name = "version")
    val version: Int = -1,

    @ColumnInfo(name = "activated")
    val activated: Int = -1,

    @ColumnInfo(name = "executions_counter")
    val executions_counter: Int = -1,

    @ColumnInfo(name = "json")
    val json: String = "",

    @ColumnInfo(name = "state")
    val state: Int = -1 //0 - Not ready, 1 - Downloading, 2 - Ready, 3 - Delete, 4 - Delete after ending, 99 - Internal

)
