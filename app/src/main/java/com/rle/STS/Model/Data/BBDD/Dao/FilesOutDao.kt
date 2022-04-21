package com.rle.STS.Model.Data.BBDD.Dao

import androidx.room.*
import com.rle.STS.Model.Data.BBDD.Model.FilesOut
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface FilesOutDao {

    @Query("SELECT * from filesOut")
    fun getFilesIn(): Flow<List<FilesOut>>

    @Query("SELECT * from filesOut where uniqueID =:id")
    suspend fun getFilesOutById(id: UUID): FilesOut

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilesOut(fileOut: FilesOut)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFilesIn(fileOut: FilesOut)

    @Query("DELETE from filesOut")
    suspend fun deleteAllFilesOut()

    @Delete
    suspend fun deleteFilesOut(fileOut: FilesOut)

}