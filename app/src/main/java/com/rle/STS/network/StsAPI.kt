package com.rle.STS.network

import com.rle.STS.model.APIs.projects.ProjectsArray
import com.rle.STS.model.APIs.projects.ProjectsResponse
import com.rle.STS.utils.Constants
import dagger.Provides
import retrofit2.http.GET
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
interface StsAPI {
    //TODO añadir header Autorization cuando esté implementado
    @GET(value = "api/sts/project/" + Constants.TOKEN)
    suspend fun getProjects(): ProjectsResponse
}