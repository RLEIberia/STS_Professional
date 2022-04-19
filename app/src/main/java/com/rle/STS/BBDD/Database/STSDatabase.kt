package com.rle.STS.BBDD.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rle.STS.BBDD.Dao.*
import com.rle.STS.BBDD.model.*
import com.rle.STS.BBDD.utils.DateConverter
import com.rle.STS.BBDD.utils.UUIDConverter

@Database(
    entities = [
        CkInstances::class,
        Checklists::class,
        FilesIn::class,
        FilesOut::class,
        Projects::class,
        Users::class,
        ViewsPersistance::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateConverter::class, UUIDConverter::class)
abstract class STSDatabase : RoomDatabase() {

    abstract fun projectsDao(): ProjectsDao
    abstract fun checklistDao(): ChecklistsDao
    abstract fun ckInstanceDao(): CkInstanceDao
    abstract fun filesInDao(): FilesInDao
    abstract fun filesOutDao(): FilesOutDao
    abstract fun usersDao(): UsersDao
    abstract fun viewsPersistanceDao(): ViewsPersistanceDao

}