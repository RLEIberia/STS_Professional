package com.rle.STS.repository

import com.rle.STS.data.BBDD.STSDao
import com.rle.STS.model.BBDD.Projects
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DatabaseRepository @Inject constructor(private val STSDao: STSDao) {

    //Projects Dao
    fun getProjects(): Flow<List<Projects>> = STSDao.getProjects()

}