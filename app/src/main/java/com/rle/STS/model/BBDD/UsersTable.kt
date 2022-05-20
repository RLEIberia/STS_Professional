package com.rle.STS.model.BBDD

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    tableName = "users_table"
)
data class UsersTable(


    //TODO - En un futuro nos tendrá que venir dado de la plataforma
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "company")
    val company: String,

    @ColumnInfo(name = "token")
    val token: String,

    @ColumnInfo(name = "projects_id")
    val projects_id: String,

    @ColumnInfo(name = "checklists_id")
    val checklists_id: String

)
