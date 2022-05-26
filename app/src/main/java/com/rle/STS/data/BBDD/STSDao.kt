package com.rle.STS.data.BBDD

import androidx.room.*
import com.rle.STS.model.BBDD.*
import kotlinx.coroutines.flow.Flow
import java.util.*
import kotlin.collections.ArrayList

@Dao
interface STSDao {

    //TODO - Añadir DAOS de las tablas que faltan y DAOS extras

    //ProjectsDao
    @Query("SELECT * from projects_table")
    fun getProjects(): Flow<List<ProjectsTable>>

    @Query("SELECT * from projects_table where id =:id")
    suspend fun getProjectById(id: Int): ProjectsTable

    @Query("SELECT * FROM projects_table WHERE id IN (:ids)")
    suspend fun getMultipleProjectsByIds(ids: Array<Int>): List<ProjectsTable>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProject(project: ProjectsTable)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMultipleProjects(projects: ArrayList<ProjectsTable>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateProject(project: ProjectsTable)

    @Query("DELETE from projects_table")
    suspend fun deleteAllProjects()

    @Delete
    suspend fun deleteProject(project: ProjectsTable)


    //ChecklistDao
    @Query("SELECT * from checklists_table")
    fun getChecklists(): Flow<List<ChecklistsTable>>

    @Query("SELECT * from checklists_table where id =:id")
    suspend fun getChecklistById(id: Int): ChecklistsTable

    @Query("SELECT * from checklists_table where id =:id")
    suspend fun getMultipleChecklistById(id: Array<Int>): List<ChecklistsTable>

    @Query("SELECT * from checklists_table where project_id=:projectId")
    suspend fun getMultipleChecklistsByProject(projectId: Int): List<ChecklistsTable>

    //TODO add checklist by id and user

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertChecklist(checklist: ChecklistsTable)

    @Insert(onConflict = OnConflictStrategy.IGNORE) //Ignore ignora si hay algo que ya esté introducido con esa primary key
    suspend fun insertMultipleChecklists(checklists: ArrayList<ChecklistsTable>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateChecklist(checklist: ChecklistsTable)

    @Query("DELETE from checklists_table")
    suspend fun deleteAllChecklists()

    @Delete
    suspend fun deleteChecklist(checklist: ChecklistsTable)

    //executionsDao
    @Query("SELECT * from executions_table")
    fun getCkInstance(): Flow<List<ExecutionsTable>>

    @Query("SELECT * from executions_table where id =:id")
    suspend fun getCkInstanceById(id: Int): ExecutionsTable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCkInstance(ckInstance: ExecutionsTable)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCkInstance(ckInstance: ExecutionsTable)

    @Query("DELETE from executions_table")
    suspend fun deleteAllCkInstances()

    @Delete
    suspend fun deleteCkInstance(ckInstance: ExecutionsTable)

    //FilesInDao
    @Query("SELECT * from file_in_table")
    fun getFilesIn(): Flow<List<FilesInTable>>

    @Query("SELECT * from file_in_table where id =:id")
    suspend fun getFilesInById(id: Int): FilesInTable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilesIn(fileInTable: FilesInTable)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFilesIn(fileInTable: FilesInTable)

    @Query("DELETE from file_in_table")
    suspend fun deleteAllFilesIn()

    @Delete
    suspend fun deleteFilesIn(fileInTable: FilesInTable)

    //FilesOutDao
    @Query("SELECT * from files_out_table")
    fun getFilesOut(): Flow<List<FilesOutTable>>

    @Query("SELECT * from files_out_table where id =:id")
    suspend fun getFilesOutById(id: Int): FilesOutTable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilesOut(fileOutTable: FilesOutTable)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFilesIn(fileOutTable: FilesOutTable)

    @Query("DELETE from files_out_table")
    suspend fun deleteAllFilesOut()

    @Delete
    suspend fun deleteFilesOut(fileOutTable: FilesOutTable)

    //UsersDao
    @Query("SELECT * from users_table")
    fun getUsers(): Flow<List<UsersTable>>

    @Query("SELECT * from users_table where id =:id")
    suspend fun getUserById(id: Int): UsersTable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UsersTable)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUser(user: UsersTable)

    @Query("DELETE from users_table")
    suspend fun deleteAllUsers()

    @Delete
    suspend fun deleteUser(user: UsersTable)


    //ViewPersistenceDao
    @Query("SELECT * from views_persistence_table")
    fun getViewPersistence(): Flow<List<ViewsPersistenceTable>>

    @Query("SELECT * from views_persistence_table where id =:id")
    suspend fun getViewPersistenceById(id: Int): ViewsPersistenceTable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertViewPersistence(viewPersistenceTableDao: ViewsPersistenceTable)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateViewPersistence(viewPersistenceTableDao: ViewsPersistenceTable)

    @Query("DELETE from views_persistence_table")
    suspend fun deleteAllViewsPersistence()

    @Query("DELETE from views_persistence_table where execution_id =:executionId")
    suspend fun deleteCkInstanceViews(executionId: Int) //Eliminar todas las vistas de una instancia de checklist

    @Delete
    suspend fun deleteViewPersistence(viewPersistenceTableDao: ViewsPersistenceTable)


    //ExecutionsDao
    @Query("SELECT * from executions_table")
    fun getExecutions(): Flow<List<ExecutionsTable>>

    @Query("SELECT * from executions_table where id =:id")
    fun getExecutionFlowById(id: UUID): Flow<List<ExecutionsTable>> //Sólo hay uno

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExecution(executionsTable: ExecutionsTable)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateExecution(executionsTable: ExecutionsTable)

    @Query("DELETE from executions_table")
    suspend fun deleteAllExecutions()

    @Query("DELETE from executions_table where id =:id")
    suspend fun deleteExecutionById(id: UUID)

    @Delete
    suspend fun deleteExecution(executionsTable: ExecutionsTable)


    //StepPersistenceDao
    @Query("SELECT * from steps_persistence_table")
    fun getStepsList(): Flow<List<StepPersistenceTable>>

    @Query("SELECT * from steps_persistence_table WHERE execution_id =:executionId AND step_id =:stepId ")
    fun getCurrentStep(executionId: UUID, stepId: Int): Flow<List<StepPersistenceTable>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStep(stepPersistenceTable: StepPersistenceTable)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateStep(stepPersistenceTable: StepPersistenceTable)

    @Query("DELETE from steps_persistence_table")
    suspend fun deleteAllSteps()

    @Query("DELETE from steps_persistence_table where id =:id")
    suspend fun deleteStepById(id: UUID)

    @Delete
    suspend fun deleteStep(stepPersistenceTable: StepPersistenceTable)

}