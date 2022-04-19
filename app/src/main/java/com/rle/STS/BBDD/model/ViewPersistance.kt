package com.rle.STS.BBDD.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    tableName = "viewPersistance",
    foreignKeys = [ForeignKey(
        entity = ChecklistInstances::class,
        parentColumns = ["uniqueID"],
        childColumns = ["idInstance"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class ViewPersistance(

    @PrimaryKey
    @ColumnInfo(name = "uniqueID")
    val uniqueId : UUID,

    @ColumnInfo(name = "idInstance")
    val idInstance : UUID,

    @ColumnInfo(name = "step")
    val step : Int,

    @ColumnInfo(name = "view")
    val view : Int,

    @ColumnInfo(name = "date")
    val date : Date,

    @ColumnInfo(name = "iteration")
    val iteration : Int,

    @ColumnInfo(name = "result")
    val result: String,

    @ColumnInfo(name = "nextStep")
    val nextStep: Int,

    @ColumnInfo(name = "lastID")
    val lastID: UUID,

    @ColumnInfo(name = "nextID")
    val nextID: UUID

)
