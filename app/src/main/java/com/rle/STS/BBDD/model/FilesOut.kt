package com.rle.STS.BBDD.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    tableName = "filesOut"
)
data class FilesOut(

    @PrimaryKey
    @ColumnInfo(name = "uniqueID")
    val uniqueID : UUID,

    @ColumnInfo(name = "instanceRef")
    val instanceRef: UUID,

    @ColumnInfo(name = "type")
    val type: Int,

    @ColumnInfo(name = "size")
    val size: Int,

    @ColumnInfo(name = "state")
    val state: Int

)
