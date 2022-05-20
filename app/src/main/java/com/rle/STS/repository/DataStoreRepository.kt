package com.rle.STS.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.rle.STS.model.DataStore.UserData
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

const val DATASTORE_NAME = "USER_DATASTORE"

val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = DATASTORE_NAME)

class DataStoreRepository @Inject constructor(private val context: Context) {

    companion object {
        val USER_CODE = intPreferencesKey("userCode")
        val TOKEN = stringPreferencesKey("token")
    }


    suspend fun saveUserData(userData: UserData) {
        context.datastore.edit { user ->
            user[USER_CODE] = userData.userCode
            user[TOKEN] = userData.token
        }
    }

//    fun getUserData() = context.datastore.data.map { user ->
//        UserData(
//            userCode = user[USER_CODE]!!,
//            token = user[TOKEN]!!
//        )
//    }

    suspend fun getUserData(): UserData {
        val user = context.datastore.data.first()
        return (UserData(
            userCode = user[USER_CODE]!!,
            token = user[TOKEN]!!
        ))
    }

}