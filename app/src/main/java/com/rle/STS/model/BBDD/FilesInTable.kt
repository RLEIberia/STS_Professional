package com.rle.STS.model.BBDD

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    tableName = "file_in_table",
    foreignKeys = [
        ForeignKey(
            entity = ChecklistsTable::class,
            parentColumns = ["id"],
            childColumns = ["id_ck_version"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)

data class FilesInTable(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "id_ck_version")
    val id_ck_version: Int, //El archivo pertenece a la versión, si los archivos se mantienen entre las versiones se les actualiza el código y los que no se hayan mantenido, al borrar la versión se eliminan

    @ColumnInfo(name = "type")
    val type: Int, //0 imagen, 1 video, 2 audio, 3 QR???, 4 PDF

    @ColumnInfo(name = "size")
    val size: Int,

    @ColumnInfo(name = "state")
    val state: Int

)
