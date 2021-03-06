package com.rle.STS.Model.Data.BBDD.Dao

import androidx.room.*
import com.rle.STS.Model.Data.BBDD.Model.Users
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface UsersDao {

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

}