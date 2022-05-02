package com.rle.STS.Model.Data.BBDD.Dao

import androidx.room.*
import com.rle.STS.Model.Data.BBDD.Model.Checklists
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface ChecklistsDao {

    @Query("SELECT * from checklists")
    fun getChecklists(): Flow<List<Checklists>>

    @Query("SELECT * from checklists where uniqueID =:id")
    suspend fun getChecklistById(id: UUID): Checklists

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChecklist(checklist: Checklists)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateChecklist(checklist: Checklists)

    @Query("DELETE from checklists")
    suspend fun deleteAllChecklists()

    @Delete
    suspend fun deleteChecklist(checklist: Checklists)

}