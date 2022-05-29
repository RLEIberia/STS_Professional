package com.rle.STS.model.BBDD

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    tableName = "file_in_table",
//    foreignKeys = [
//        ForeignKey(
//            entity = ChecklistsTable::class,
//            parentColumns = ["id"],
//            childColumns = ["id_ck_version"],
//            onDelete = ForeignKey.CASCADE
//        )
//    ]
)

data class FilesInTable(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int = -1, //Viene desde plataforma

    @ColumnInfo(name = "name")
    val name: String = "",

    @ColumnInfo(name = "description")
    val description: String = "",

    @ColumnInfo(name = "file")
    val file: String = "",

    @ColumnInfo(name = "date")
    val date: Long = -1,

    @ColumnInfo(name = "id_ck_version")
    val id_ck_version: Int = -1, //El archivo pertenece a la versión, si los archivos se mantienen entre las versiones se les actualiza el código y los que no se hayan mantenido, al borrar la versión se eliminan

    @ColumnInfo(name = "type")
    val type: Int = -1, //0 imagen, 1 video, 2 audio, 3 QR???, 4 PDF

    @ColumnInfo(name = "size")
    val size: Int = -1,

    @ColumnInfo(name = "state")
    val state: Int = -1

)
