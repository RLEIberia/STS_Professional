package com.rle.STS.Model.Data.BBDD.Dao

import androidx.room.*
import com.rle.STS.Model.Data.BBDD.Model.CkInstances
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface CkInstanceDao {

    @Query("SELECT * from checklistInstances")
    fun getCkInstance(): Flow<List<CkInstances>>

    @Query("SELECT * from checklistInstances where uniqueID =:id")
    suspend fun getCkInstanceById(id: UUID): CkInstances

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCkInstance(ckInstance: CkInstances)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCkInstance(ckInstance: CkInstances)

    @Query("DELETE from checklistInstances")
    suspend fun deleteAllCkInstances()

    @Delete
    suspend fun deleteCkInstance(ckInsntace: CkInstances)

}