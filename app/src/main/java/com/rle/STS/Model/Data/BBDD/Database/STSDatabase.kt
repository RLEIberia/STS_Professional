package com.rle.STS.Model.Data.BBDD.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rle.STS.Model.Data.BBDD.Dao.*
import com.rle.STS.Model.Data.BBDD.Model.*
import com.rle.STS.Model.Data.BBDD.Utils.DateConverter
import com.rle.STS.Model.Data.BBDD.Utils.UUIDConverter

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