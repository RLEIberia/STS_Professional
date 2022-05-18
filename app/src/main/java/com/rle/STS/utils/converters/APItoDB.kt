package com.rle.STS.utils.converters

import com.rle.STS.data.DataOrException
import com.rle.STS.model.APIs.projects.Checklist
import com.rle.STS.model.APIs.projects.Project
import com.rle.STS.model.APIs.projects.ProjectsArray
import com.rle.STS.model.APIs.projects.ProjectsResponse
import com.rle.STS.model.BBDD.ChecklistsTable
import com.rle.STS.model.BBDD.ProjectsTable

fun toProjectsTable(apiProjectsResponse: DataOrException<ProjectsResponse, Boolean, Exception>): ArrayList<ProjectsTable>{

    val projectsTableArray =
        ArrayList(apiProjectsResponse.data!!.response.projects.map { project ->
            ProjectsTable(
                id = project.id,
                name = project.name,
                description = project.description,
                version = project.version,
                activated = project.activated,
                created_at = project.created_at,
                updated_at = project.updated_at,
                state = 0
            )
        })

    return projectsTableArray

}


fun toChecklistsTable(apiProjectsResponse: DataOrException<ProjectsResponse, Boolean, Exception>): ArrayList<ChecklistsTable>{

    val checklistsTableArray: ArrayList<ChecklistsTable> = ArrayList()

    apiProjectsResponse.data!!.response.projects.forEach { project ->
        checklistsTableArray.addAll(
            ArrayList(project.checklists.map { checklist ->
                ChecklistsTable(
                    id = checklist.id,
                    checklist_id = checklist.checklist_id,
                    project_id = checklist.project_id,
                    name = checklist.name,
                    description = checklist.description,
                    version = checklist.version,
                    activated = checklist.activated,
                    created_at = checklist.created_at,
                    updated_at = checklist.updated_at,
                    executions_counter = 0,
                    json = "", //TODO - Cambiar inicialización de nombre de JSON - Definir formato interno
                    state = 0
                )
            })
        )
    }

    return checklistsTableArray


}
