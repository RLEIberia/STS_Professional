package com.rle.STS.Model.Data.BBDD.Dao

import androidx.room.*
import com.rle.STS.Model.Data.BBDD.Model.ViewsPersistance
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

    @Query("DELETE from viewsPersistance where idInstance =:ckInstanceID")
    suspend fun deleteCkInstanceViews(ckInstanceID: UUID) //Eliminar todas las vistas de una instancia de checklist

    @Delete
    suspend fun deleteViewPersistance(viewPersistanceDao: ViewsPersistance)



}