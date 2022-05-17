package com.rle.STS.model.BBDD

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.util.TableInfo
import java.util.*

@Entity(
    tableName = "views_persistence_table",
    foreignKeys = [
        ForeignKey(
            entity = ExecutionsTable::class,
            parentColumns = ["id"],
            childColumns = ["execution_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = StepPersistenceTable::class,
            parentColumns = ["id"],
            childColumns = ["step_persistence_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ViewsPersistenceTable(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: UUID,

    @ColumnInfo(name = "execution_id")
    val execution_id: UUID,

    @ColumnInfo(name = "step_persistence_id")
    val step_persistence_id: UUID,

    @ColumnInfo(name = "view")
    val view: Int,

    @ColumnInfo(name = "date")
    val date: Long,

    @ColumnInfo(name = "result")
    val result: String,

    @ColumnInfo(name = "extra_data")
    val extra_data: String, //Información extra simple, añadir textos, números extra, etc.

    @ColumnInfo(name = "extra_file")
    val extra_file: String, //Archivos extra, audios, fotos, vídeos, etc.

)
