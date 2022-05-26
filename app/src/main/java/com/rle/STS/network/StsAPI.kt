package com.rle.STS.network

import com.google.gson.JsonObject
import com.rle.STS.model.APIs.projects.ProjectsArray
import com.rle.STS.model.APIs.projects.ProjectsResponse
import com.rle.STS.utils.Constants
import dagger.Provides
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
interface StsAPI {
    //TODO añadir header Autorization cuando esté implementado
    @GET(value = "api/stsapi/project/" + Constants.TOKEN)
    suspend fun getProjects(): ProjectsResponse

//    @POST(value = "api/stsapi/checklistdata/" + Constants.TOKEN)
//    suspend fun uploadExecution(@Body data: testPost): Call<*>
}

//data class testPost(val data: JsonObject)

//Dto