package com.rle.STS.model.BBDD

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    tableName = "filesIn"
)
data class FilesIn(

    @PrimaryKey
    @ColumnInfo(name = "uniqueID")
    val uniqueID : UUID,

    @ColumnInfo(name = "checklistCode")
    val checklistCode: String,

    @ColumnInfo(name = "type")
    val type: Int,

    @ColumnInfo(name = "size")
    val size: Int,

    @ColumnInfo(name = "state")
    val state: Int

)
