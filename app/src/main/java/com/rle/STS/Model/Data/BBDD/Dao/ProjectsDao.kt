package com.rle.STS.Model.Data.BBDD.Dao

import androidx.room.*
import com.rle.STS.Model.Data.BBDD.Model.Projects
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface ProjectsDao {

    @Query("SELECT * from projects")
    fun getProjects(): Flow<List<Projects>>

    @Query("SELECT * from projects where uniqueID =:id")
    suspend fun getProjectById(id: UUID): Projects

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProject(project: Projects)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateProject(project: Projects)

    @Query("DELETE from projects")
    suspend fun deleteAllProjects()

    @Delete
    suspend fun deleteProject(project: Projects)

}