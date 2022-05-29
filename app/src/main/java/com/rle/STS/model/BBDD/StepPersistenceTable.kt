package com.rle.STS.model.BBDD

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.util.UUIDUtil
import java.util.*

@Entity(
    tableName = "steps_persistence_table",
    foreignKeys = [
        ForeignKey(
            entity = ExecutionsTable::class,
            parentColumns = ["id"],
            childColumns = ["execution_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)

data class StepPersistenceTable(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long? = null,

    @ColumnInfo(name = "execution_id")
    val execution_id: Long,

    @ColumnInfo(name = "step_id")
    val step_id: Int, //Viene desde plataforma

    @ColumnInfo(name = "step")
    val step: Int,

    @ColumnInfo(name = "result_code")
    val result_code: Int, //0 es que no se ha introducido

    @ColumnInfo(name = "iteration")
    val iteration: Int,

    @ColumnInfo(name = "finished")
    val finished: Boolean,

    @ColumnInfo(name = "last_iteration_check")
    val last_iteration_check: Boolean,

    @ColumnInfo(name = "last_step_id")
    val last_step_id: Long, //id a esta tabla del anterior paso

    @ColumnInfo(name = "next_step_id")
    val next_step_id: Long //id a esta tabla del siguiente paso

)
