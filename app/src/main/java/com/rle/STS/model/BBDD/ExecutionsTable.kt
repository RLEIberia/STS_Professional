package com.rle.STS.model.BBDD

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    tableName = "executions_table",
    foreignKeys = [
        ForeignKey(
            entity = ChecklistsTable::class,
            parentColumns = ["id"],
            childColumns = ["id_ck_version"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = UsersTable::class,
            parentColumns = ["id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ExecutionsTable(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long? = null,

    @ColumnInfo(name = "user_id")
    val user_id: Int = -1,

    @ColumnInfo(name = "id_ck_version")
    val id_ck_version: Int = -1,

    @ColumnInfo(name = "created_at")
    val created_at: Long = -1,

    @ColumnInfo(name = "updated_at")
    val updated_at: Long = -1,

    @ColumnInfo(name = "current_step")
    val current_step: Int = -1,

    @ColumnInfo(name = "current_view")
    val current_view: Int = -1,

    @ColumnInfo(name = "json_result")
    val json_result: String = "",

    @ColumnInfo(name = "state")
    val state: Int = 1 //1 - En curso, 2 - Finalizada, 3 - "No completada", 4 - Enviada, 5 - Eliminar

)
