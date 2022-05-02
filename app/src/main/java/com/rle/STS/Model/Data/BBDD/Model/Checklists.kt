package com.rle.STS.Model.Data.BBDD.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    tableName = "checklists",
    foreignKeys = [ForeignKey(
        entity = Projects::class,
        parentColumns = ["projectCode"],
        childColumns = ["projectCode"],
        onDelete = CASCADE
    )]
)
data class Checklists(

    @PrimaryKey
    @ColumnInfo(name = "uniqueID")
    val uniqueID: UUID,

    @ColumnInfo(name = "checklistCode")
    val checklistCode: String,

    @ColumnInfo(name = "projectCode")
    val projectCode: String,

    @ColumnInfo(name = "creationDate")
    val creationDate: Date,

    @ColumnInfo(name = "updateDate")
    val updateDate: Date,

    @ColumnInfo(name = "version")
    val version: Int,

    @ColumnInfo(name = "executionsCouter")
    val executionsCounter: Int,

    @ColumnInfo(name = "jsonReference")
    val jsonReference: String,

    @ColumnInfo(name = "state")
    val state: Int //0, 1, 2, 3

)
