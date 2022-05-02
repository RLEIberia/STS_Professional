package com.rle.STS.Model.Data.BBDD.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    tableName = "users"
)
data class Users(

    @PrimaryKey
    @ColumnInfo(name = "uniqueID")
    val uniqueID: UUID,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "company")
    val company: String,

    @ColumnInfo(name = "token")
    val token: String,

    @ColumnInfo(name = "codeProjectsList")
    val codePorjectsList: String,

    @ColumnInfo(name = "codeChecklist")
    val codeChecklistsList: String

)
