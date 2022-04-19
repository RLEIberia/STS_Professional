package com.rle.STS.BBDD.Dao

import androidx.room.*
import com.rle.STS.BBDD.model.CkInstances
import com.rle.STS.BBDD.model.FilesIn
import kotlinx.coroutines.flow.Flow
import java.nio.file.Files
import java.util.*

@Dao
interface FilesInDao {

    @Query("SELECT * from filesIn")
    fun getFilesIn(): Flow<List<FilesIn>>

    @Query("SELECT * from filesIn where uniqueID =:id")
    suspend fun getFilesInById(id: UUID): FilesIn

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilesIn(fileIn: FilesIn)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFilesIn(fileIn: FilesIn)

    @Query("DELETE from filesIn")
    suspend fun deleteAllFilesIn()

    @Delete
    suspend fun deleteFilesIn(fileIn: FilesIn)

}