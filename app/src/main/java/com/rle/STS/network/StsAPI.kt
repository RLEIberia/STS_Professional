package com.rle.STS.network

import com.rle.STS.model.APIs.projects.ProjectsResponse
import com.rle.STS.items.Network
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface StsAPI {
    //TODO añadir header Autorization cuando esté implementado
    @GET(value = "api/stsapi/project/" + Network.TOKEN)
    suspend fun getProjects(): ProjectsResponse

//    @POST(value = "api/stsapi/checklistdata/" + Constants.TOKEN)
//    suspend fun uploadExecution(@Body data: testPost): Call<*>
}

//data class testPost(val data: JsonObject)

//Dto