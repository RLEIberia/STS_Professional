package com.rle.STS.repository

import com.rle.STS.data.BBDD.STSDao
import com.rle.STS.model.BBDD.ChecklistsTable
import com.rle.STS.model.BBDD.ProjectsTable
import com.rle.STS.model.BBDD.UsersTable
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DbRepository @Inject constructor(private val STSDao: STSDao) {

    //Projects Dao
    fun getProjects(): Flow<List<ProjectsTable>> = STSDao.getProjects()
    suspend fun getProjectById(id: Int) = STSDao.getProjectById(id)
    suspend fun getMultipleProjectsByIds(ids: Array<Int>) = STSDao.getMultipleProjectsByIds(ids)
    suspend fun insertProject(project: ProjectsTable) = STSDao.insertProject(project)
    suspend fun insertMultipleProjects(projects: ArrayList<ProjectsTable>) = STSDao.insertMultipleProjects(projects)
    suspend fun updateProject(project: ProjectsTable) = STSDao.updateProject(project)
    suspend fun deleteAllProjects() = STSDao.deleteAllProjects()
    suspend fun deleteProject(project: ProjectsTable) = STSDao.deleteProject(project)


    //Checklists Dao
    fun getChecklists(): Flow<List<ChecklistsTable>> = STSDao.getChecklists()
    suspend fun getChecklistById(id: Int) = STSDao.getChecklistById(id)
    suspend fun getMultipleChecklistsByProject (projectId: Int) = STSDao.getMultipleChecklistsByProject(projectId)
    suspend fun insertChecklist(checklist: ChecklistsTable) = STSDao.insertChecklist(checklist)
    suspend fun insertMultipleChecklists(checklists: ArrayList<ChecklistsTable>) = STSDao.insertMultipleChecklists(checklists)
    suspend fun updateChecklist(checklist: ChecklistsTable) = STSDao.updateChecklist(checklist)
    suspend fun deleteAllChecklists() = STSDao.deleteAllChecklists()
    suspend fun deleteChecklist(checklist: ChecklistsTable) = STSDao.deleteChecklist(checklist)

    //Users Dao
    fun getUsers(): Flow<List<UsersTable>> = STSDao.getUsers()
    suspend fun getUserById(id: Int) = STSDao.getUserById(id)
    suspend fun insertUser(user: UsersTable) = STSDao.insertUser(user)
    suspend fun updateUser(user: UsersTable) = STSDao.updateUser(user)
    suspend fun deleteAllUsers() = STSDao.deleteAllUsers()
    suspend fun deleteUser(user: UsersTable) = STSDao.deleteUser(user)

    //ViewPersistenceDao

}