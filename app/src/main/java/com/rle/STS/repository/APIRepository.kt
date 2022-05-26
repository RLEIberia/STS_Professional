package com.rle.STS.repository

import android.content.Context
import android.util.Log
import com.google.gson.JsonObject
import com.rle.STS.data.DataOrException
import com.rle.STS.model.APIs.projects.Checklist
import com.rle.STS.model.APIs.projects.ProjectsResponse
import com.rle.STS.model.BBDD.ChecklistsTable
import com.rle.STS.network.StsAPI
import com.rle.STS.utils.checkForInternet
import org.json.JSONObject
import retrofit2.HttpException
import javax.inject.Inject

class APIRepository @Inject constructor(
    private val api: StsAPI
) {

    suspend fun getProjects(): DataOrException<ProjectsResponse, Boolean, Exception> {
        val response =
            try {
                api.getProjects()
            } catch (e: Exception) {
                Log.d("REX", "getProjects: $e")
                return DataOrException(e = e)
            }
        Log.d("INSIDE", "getProjects: $response")
        return DataOrException(data = response)
    }

//    suspend fun uploadExecution(data: testPost): DataOrException<String, Boolean, Exception> {
//        val response =
//            try {
//                api.uploadExecution(data)
//            } catch (e: Exception) {
//                Log.d("REX", "uploadExecution: $e")
//                return DataOrException(e = e)
//            }
//        Log.d("INSIDE", "uploadExecution: $response")
//        return DataOrException(data = response.toString())
//    }


    fun checkForInternet(context: Context) =
        com.rle.STS.utils.checkForInternet(context = context)


}