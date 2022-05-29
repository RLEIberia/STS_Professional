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

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long? = null,

    @ColumnInfo(name = "execution_id")
    val execution_id: Long,

    @ColumnInfo(name = "view_id")
    val view_id: Int, //viene dado desde plataforma

    @ColumnInfo(name = "view")
    val view: Int,

    @ColumnInfo(name = "step_persistence_id")
    val step_persistence_id: Long,

    @ColumnInfo(name = "date")
    val updated_at: Long,

    @ColumnInfo(name = "result")
    val result: String,

    @ColumnInfo(name = "extra_data")
    val extra_data: String, //Información extra simple, añadir textos, números extra, etc.

    @ColumnInfo(name = "extra_file")
    val extra_file: String, //Archivos extra, audios, fotos, vídeos, etc.

)
