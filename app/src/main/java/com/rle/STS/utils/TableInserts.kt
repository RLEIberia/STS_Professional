package com.rle.STS.utils

import com.rle.STS.model.BBDD.ChecklistsTable
import com.rle.STS.model.BBDD.UsersTable

object TableInserts {

    //User
    val user1 = UsersTable(
        id = 1,
        name = "Jose Cabello",
        company = "RLE International Iberia",
        token = "2d4b6637bfaa6224cd08f31a79ebf9ab",
        projects_id = "1,2",
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

}