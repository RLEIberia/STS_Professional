package com.rle.STS.BBDD.Dao

import androidx.room.Dao
import androidx.room.Query
import com.rle.STS.BBDD.model.Projects
import kotlinx.coroutines.flow.Flow

@Dao
interface ProjectsDao {

    @Query("SELECT * from projects")
    fun getProjects():
            Flow<List<Projects>>

}