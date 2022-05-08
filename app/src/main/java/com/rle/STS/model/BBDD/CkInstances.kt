package com.rle.STS.model.BBDD

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    tableName = "checklistInstances",
    foreignKeys = [ForeignKey(
        entity = ChecklistsTable::class,
        parentColumns = ["uniqueID"],
        childColumns = ["idVerChecklist"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class CkInstances(

    @PrimaryKey
    @ColumnInfo(name = "uniqueID")
    val uniqueID: UUID,

    @ColumnInfo(name = "idVerChecklist")
    val idVerChecklist: String,

    @ColumnInfo(name = "creationDate")
    val creationDate: Date,

    @ColumnInfo(name = "updateDate")
    val updateDate: Date,

    @ColumnInfo(name = "state")
    val state: Int,

    @ColumnInfo(name = "current")
    val current: UUID,

    @ColumnInfo(name = "jsonResult")
    val jsonResult: String

)
