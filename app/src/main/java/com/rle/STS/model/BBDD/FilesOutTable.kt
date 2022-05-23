package com.rle.STS.model.BBDD

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    tableName = "files_out_table",
    foreignKeys = [
        ForeignKey(
            entity = ExecutionsTable::class,
            parentColumns = ["id"],
            childColumns = ["execution_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class FilesOutTable(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: UUID, //También será el nombre del fichero

    @ColumnInfo(name = "execution_id")
    val execution_id: UUID,

    @ColumnInfo(name = "type")
    val type: Int, //0 imagen, 1 video, 2 audio, 3 QR???, 4 PDF

    @ColumnInfo(name = "size")
    val size: Int, //bytes

    @ColumnInfo(name = "state")
    val state: Int

)