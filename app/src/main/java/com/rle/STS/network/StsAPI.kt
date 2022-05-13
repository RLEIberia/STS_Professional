package com.rle.STS.network

import com.rle.STS.model.APIs.projects.ProjectsArray
import com.rle.STS.model.APIs.projects.ProjectsResponse
import com.rle.STS.utils.Constants
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface StsAPI {


    //TODO añadir header Autorization cuando esté implementado
    @GET(value = "project/" + Constants.TOKEN)
    suspend fun getProjects(): ProjectsResponse

}