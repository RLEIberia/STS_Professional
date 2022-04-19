package com.rle.STS.BBDD.Dao

import androidx.room.*
import com.rle.STS.BBDD.model.ViewsPersistance
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface ViewsPersistanceDao {

    @Query("SELECT * from viewsPersistance")
    fun getUsers(): Flow<List<ViewsPersistance>>

    @Query("SELECT * from viewsPersistance where uniqueID =:id")
    suspend fun getViewPersistanceById(id: UUID): ViewsPersistance

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertViewPersistance(viewPersistanceDao: ViewsPersistance)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateViewPersistance(viewPersistanceDao: ViewsPersistance)

    @Query("DELETE from viewsPersistance")
    suspend fun deleteAllViewsPersistance()

    @Delete
    suspend fun deleteViewPersistance(viewPersistanceDao: ViewsPersistance)

}