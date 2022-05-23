package com.rle.STS.utils

import com.rle.STS.model.BBDD.ChecklistsTable
import com.rle.STS.model.BBDD.ProjectsTable
import com.rle.STS.model.BBDD.UsersTable

object TableInserts {

    //User
    val user1 = UsersTable(
        id = 1,
        name = "Jose Cabello",
        company = "RLE International Iberia",
        token = "2d4b6637bfaa6224cd08f31a79ebf9ab",
        projects_id = "1,2,3,1001,1002,1003,1004,1005,1006",
        checklists_id = "111,323,231,10001"
    )

    val user2 = UsersTable(
        id = 2,
        name = "Alberto Ortega",
        company = "RLE International Iberia",
        token = "2d4b6637bfaa6224cd08f31a79ebf9ac",
        projects_id = "1,2",
        checklists_id = "1"
    )

    //Checklist
    val checklistExtra1 = ChecklistsTable(
        id = 10001,
        checklist_id = 10001,
        project_id = 2,
        name = "Checklist extra prueba",
        description = "Checklist insertada manualmente a modo de prueba",
        created_at = 1,
        updated_at = 2,
        version = 1,
        activated = 1,
        executions_counter = 0,
        json = "",
        state = 3
    )

    val projectExtra1 = ProjectsTable(
        id = 1001,
        name = "Proyecto de prueba 1001",
        description = "Descripción del proyecto",
        version = 1,
        activated = 1,
        created_at = 1,
        updated_at = 1,
        state = 4
    )

    val projectExtra2 = ProjectsTable(
        id = 1002,
        name = "Proyecto de prueba 1002",
        description = "Descripción del proyecto",
        version = 1,
        activated = 1,
        created_at = 1,
        updated_at = 1,
        state = 4
    )

    val projectExtra3 = ProjectsTable(
        id = 1003,
        name = "Proyecto de prueba 1003",
        description = "Descripción del proyecto",
        version = 1,
        activated = 1,
        created_at = 1,
        updated_at = 1,
        state = 4
    )
    val projectExtra4 = ProjectsTable(
        id = 1004,
        name = "Proyecto de prueba 1004",
        description = "Descripción del proyecto",
        version = 1,
        activated = 1,
        created_at = 1,
        updated_at = 1,
        state = 4
    )
    val projectExtra5 = ProjectsTable(
        id = 1005,
        name = "Proyecto de prueba 1005",
        description = "Descripción del proyecto",
        version = 1,
        activated = 1,
        created_at = 1,
        updated_at = 1,
        state = 4
    )
    val projectExtra6 = ProjectsTable(
        id = 1006,
        name = "Proyecto de prueba 1006",
        description = "Descripción del proyecto",
        version = 1,
        activated = 1,
        created_at = 1,
        updated_at = 1,
        state = 4
    )

}