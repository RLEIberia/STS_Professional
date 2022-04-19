package com.rle.STS.BBDD.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rle.STS.BBDD.Dao.ProjectsDao
import com.rle.STS.BBDD.model.*
import com.rle.STS.BBDD.utils.DateConverter
import com.rle.STS.BBDD.utils.UUIDConverter

@Database(
    entities = [
        ChecklistInstances::class,
        Checklists::class,
        FilesIn::class,
        FilesOut::class,
        Projects::class,
        Users::class,
        ViewPersistance::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateConverter::class, UUIDConverter::class)
abstract class STSDatabase : RoomDatabase() {

    abstract fun projectsDao(): ProjectsDao


}