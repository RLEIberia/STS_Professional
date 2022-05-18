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

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: UUID,

    @ColumnInfo(name = "user_id")
    val user_id: Int,

    @ColumnInfo(name = "id_ck_version")
    val id_ck_version: Int,

    @ColumnInfo(name = "created_at")
    val created_at: Long,

    @ColumnInfo(name = "updated_at")
    val updated_at: Long,

    @ColumnInfo(name = "current_step")
    val current_step: Int,

    @ColumnInfo(name = "current_view")
    val current_view: Int,

    @ColumnInfo(name = "json_result")
    val json_result: String,

    @ColumnInfo(name = "state")
    val state: Int //1 - En curso, 2 - Finalizada, 3 - "No completada", 4 - Enviada, 5 - Eliminar

)
