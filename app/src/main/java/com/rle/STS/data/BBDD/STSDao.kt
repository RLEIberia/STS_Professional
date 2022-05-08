package com.rle.STS.data.BBDD

import androidx.room.*
import com.rle.STS.model.BBDD.*
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface STSDao {

    //ChecklistDao
    @Query("SELECT * from checklists_table")
    fun getChecklists(): Flow<List<ChecklistsTable>>

    @Query("SELECT * from checklists_table where uniqueID =:id")
    suspend fun getChecklistById(id: UUID): ChecklistsTable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChecklist(checklist: ChecklistsTable)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateChecklist(checklist: ChecklistsTable)

    @Query("DELETE from checklists_table")
    suspend fun deleteAllChecklists()

    @Delete
    suspend fun deleteChecklist(checklist: ChecklistsTable)

    //CkInstanceDao
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
    suspend fun deleteCkInstance(ckInstance: CkInstances)

    //FilesInDao
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

    //FilesOutDao
    @Query("SELECT * from filesOut")
    fun getFilesOut(): Flow<List<FilesOut>>

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

    //ProjectsDao
    @Query("SELECT * from projects_table")
    fun getProjects(): Flow<List<Projects>>

    @Query("SELECT * from projects_table where uniqueID =:id")
    suspend fun getProjectById(id: UUID): Projects

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProject(project: Projects)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateProject(project: Projects)

    @Query("DELETE from projects_table")
    suspend fun deleteAllProjects()

    @Delete
    suspend fun deleteProject(project: Projects)

    //UsersDao
    @Query("SELECT * from users")
    fun getUsers(): Flow<List<Users>>

    @Query("SELECT * from users where uniqueID =:id")
    suspend fun getUserById(id: UUID): Users

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: Users)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUser(user: Users)

    @Query("DELETE from users")
    suspend fun deleteAllUsers()

    @Delete
    suspend fun deleteUser(user: Users)

    //ViewPersistenceDao
    @Query("SELECT * from viewsPersistence")
    fun getViewPersistance(): Flow<List<ViewsPersistence>>

    @Query("SELECT * from viewsPersistence where uniqueID =:id")
    suspend fun getViewPersistenceById(id: UUID): ViewsPersistence

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertViewPersistence(viewPersistenceDao: ViewsPersistence)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateViewPersistence(viewPersistenceDao: ViewsPersistence)

    @Query("DELETE from viewsPersistence")
    suspend fun deleteAllViewsPersistence()

    @Query("DELETE from viewsPersistence where idInstance =:ckInstanceID")
    suspend fun deleteCkInstanceViews(ckInstanceID: UUID) //Eliminar todas las vistas de una instancia de checklist

    @Delete
    suspend fun deleteViewPersistence(viewPersistenceDao: ViewsPersistence)



}