package com.rle.STS.Model.Data.BBDD.Model

import androidx.room.*
import java.util.*

@Entity(
    tableName = "projects",
    indices = [Index(value = ["projectCode"], unique = true)]
)
data class Projects(

    @PrimaryKey
    @ColumnInfo(name = "uniqueID")
    val uniqueID: UUID,

    @ColumnInfo(name = "projectCode")
    val projectCode: String,

    @ColumnInfo(name = "projectName")
    val projectName: String,

    @ColumnInfo(name = "creationDate")
    val creationDate: Date,

    @ColumnInfo(name = "updateDate")
    val updateDate: Date,

    @ColumnInfo(name = "version")
    val version: Int


)
