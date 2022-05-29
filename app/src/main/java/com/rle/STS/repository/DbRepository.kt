package com.rle.STS.repository

import com.rle.STS.data.BBDD.STSDao
import com.rle.STS.model.BBDD.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class DbRepository @Inject constructor(private val STSDao: STSDao) {

    //Projects Dao
    fun getProjects(): Flow<List<ProjectsTable>> = STSDao.getProjects().flowOn(Dispatchers.IO)
        .conflate()
    suspend fun getProjectById(id: Int) = STSDao.getProjectById(id)
    suspend fun getMultipleProjectsByIds(ids: Array<Int>) = STSDao.getMultipleProjectsByIds(ids)
    suspend fun insertProject(project: ProjectsTable) = STSDao.insertProject(project)
    suspend fun insertMultipleProjects(projects: ArrayList<ProjectsTable>) =
        STSDao.insertMultipleProjects(projects)

    suspend fun updateProject(project: ProjectsTable) = STSDao.updateProject(project)
    suspend fun deleteAllProjects() = STSDao.deleteAllProjects()
    suspend fun deleteProject(project: ProjectsTable) = STSDao.deleteProject(project)


    //Checklists Dao
    fun getChecklists(): Flow<List<ChecklistsTable>> = STSDao.getChecklists().flowOn(Dispatchers.IO)
        .conflate()
    suspend fun getChecklistById(id: Int) = STSDao.getChecklistById(id)
    suspend fun getMultipleChecklistsByProject(projectId: Int) =
        STSDao.getMultipleChecklistsByProject(projectId)
    suspend fun getChecklistByName(name: String) = STSDao.getChecklistByName(name = name)

    suspend fun insertChecklist(checklist: ChecklistsTable) = STSDao.insertChecklist(checklist)
    suspend fun insertMultipleChecklists(checklists: ArrayList<ChecklistsTable>) =
        STSDao.insertMultipleChecklists(checklists)

    suspend fun updateChecklist(checklist: ChecklistsTable) = STSDao.updateChecklist(checklist)
    suspend fun deleteAllChecklists() = STSDao.deleteAllChecklists()
    suspend fun deleteChecklist(checklist: ChecklistsTable) = STSDao.deleteChecklist(checklist)

    //Users Dao
    fun getUsers(): Flow<List<UsersTable>> = STSDao.getUsers().flowOn(Dispatchers.IO)
        .conflate()
    suspend fun getUserById(id: Int) = STSDao.getUserById(id)
    suspend fun insertUser(user: UsersTable) = STSDao.insertUser(user)
    suspend fun updateUser(user: UsersTable) = STSDao.updateUser(user)
    suspend fun deleteAllUsers() = STSDao.deleteAllUsers()
    suspend fun deleteUser(user: UsersTable) = STSDao.deleteUser(user)

    //Executions Dao
    fun getExecutions(): Flow<List<ExecutionsTable>> = STSDao.getExecutions()
    fun getExecutionFlowById(id: UUID): Flow<List<ExecutionsTable>> =
        STSDao.getExecutionFlowById(id = id).flowOn(Dispatchers.IO)
            .conflate()
    suspend fun insertExecution(executionsTable: ExecutionsTable) =
        STSDao.insertExecution(executionsTable = executionsTable)
    suspend fun updateExecution(executionsTable: ExecutionsTable) =
        STSDao.updateExecution(executionsTable = executionsTable)
    suspend fun deleteAllExecutions() = STSDao.deleteAllExecutions()
    suspend fun deleteExecutionsById(id: UUID) = STSDao.deleteExecutionById(id = id)
    suspend fun deleteExecution(executionsTable: ExecutionsTable) =
        STSDao.deleteExecution(executionsTable = executionsTable)

    //Steps Dao
    fun getStepList(): Flow<List<StepPersistenceTable>> = STSDao.getStepsList().flowOn(Dispatchers.IO)
        .conflate()
    fun getCurrentStep(executionId: UUID, step: Int): Flow<List<StepPersistenceTable>> =
        STSDao.getCurrentStep(executionId = executionId, step = step).flowOn(Dispatchers.IO)
            .conflate()
    suspend fun insertStep(stepPersistenceTable: StepPersistenceTable) =
        STSDao.insertStep(stepPersistenceTable = stepPersistenceTable)
    suspend fun updateStep(stepPersistenceTable: StepPersistenceTable) =
        STSDao.updateStep(stepPersistenceTable = stepPersistenceTable)
    suspend fun deleteAllSteps() = STSDao.deleteAllSteps()
    suspend fun deleteStepById(id: UUID) = STSDao.deleteStepById(id = id)
    suspend fun deleteStep(stepPersistenceTable: StepPersistenceTable) =
        STSDao.deleteStep(stepPersistenceTable = stepPersistenceTable)


    //ViewPersistenceDao
    fun getViewPersistence(): Flow<List<ViewsPersistenceTable>> = STSDao.getViewPersistence()
        .flowOn(Dispatchers.IO).conflate()
    fun getCurrentView(stepPersistenceId: UUID, view: Int): Flow<List<ViewsPersistenceTable>> =
        STSDao.getCurrentView(stepPersistenceId = stepPersistenceId, view = view).flowOn(Dispatchers.IO)
            .conflate()
    fun getCurrentStepViews(stepPersistenceId: UUID): Flow<List<ViewsPersistenceTable>> =
        STSDao.getCurrentStepViews(stepPersistenceId = stepPersistenceId).flowOn(Dispatchers.IO)
            .conflate()
    suspend fun getViewPersistenceById(id: UUID) : ViewsPersistenceTable =
        STSDao.getViewPersistenceById(id = id)
    suspend fun insertViewPersistence(viewsPersistenceTable: ViewsPersistenceTable) =
        STSDao.insertViewPersistence(viewPersistenceTableDao = viewsPersistenceTable)
    suspend fun inserMutlipleViewPersistence(viewsTableList: ArrayList<ViewsPersistenceTable>) =
        STSDao.insertMultipleViewPersistence(viewsTableList = viewsTableList)
    suspend fun updateViewPersistence(viewsPersistenceTable: ViewsPersistenceTable) =
        STSDao.updateViewPersistence(viewPersistenceTable = viewsPersistenceTable)
    suspend fun deleteAllViewsPersistence() = STSDao.deleteAllViewsPersistence()
    suspend fun deleCkInstanceViews(executionId: UUID) =
        STSDao.deleteCkInstanceViews(executionId = executionId)
    suspend fun deleteViewPersistence(viewsPersistenceTable: ViewsPersistenceTable) =
        STSDao.deleteViewPersistence(viewPersistenceTable = viewsPersistenceTable)

    //FilesIn
    fun getFilesIn(): Flow<List<FilesInTable>> = STSDao.getFilesIn().flowOn(Dispatchers.IO)
        .conflate()
    fun getFilesByTypes(types: Array<Int>) = STSDao.getFilesByTypes(types = types)
    suspend fun getFileById(id: Int) = STSDao.getFilesInById(id = id)
    suspend fun getFilesByName(name: String) = STSDao.getFilesInByName(name = name)
    suspend fun insertFileIn(file: FilesInTable) = STSDao.insertFilesIn(fileInTable = file)
}