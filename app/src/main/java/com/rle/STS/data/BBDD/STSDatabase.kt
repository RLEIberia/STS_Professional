package com.rle.STS.data.BBDD

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rle.STS.model.BBDD.*
import com.rle.STS.utils.converters.DateConverter
import com.rle.STS.utils.converters.UUIDConverter

@Database(
    entities = [
        ExecutionsTable::class,
        ChecklistsTable::class,
        FilesInTable::class,
        FilesOutTable::class,
        ProjectsTable::class,
        UsersTable::class,
        StepPersistenceTable::class,
        ViewsPersistenceTable::class
    ],
    version = 18,
    exportSchema = false
)
@TypeConverters(DateConverter::class, UUIDConverter::class)
abstract class STSDatabase : RoomDatabase() {

    abstract fun STSDao(): STSDao
    //TODO stepDao

}