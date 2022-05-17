package com.rle.STS.repository

import android.util.Log
import com.rle.STS.data.DataOrException
import com.rle.STS.model.APIs.projects.ProjectsResponse
import com.rle.STS.network.StsAPI
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

}