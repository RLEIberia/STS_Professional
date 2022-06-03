package com.rle.STS.items

import com.rle.STS.model.BBDD.ChecklistsTable
import com.rle.STS.model.BBDD.FilesInTable
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

    //0 imagen, 1 video, 2 audio, 3 QR???, 4 PDF
    val fileExtra1 = FilesInTable(
        id = 1001,
        name = "Especificaciones de tractora",
        description = "Documento de descripción TGR 1846 4X2 ELEGANCE",
        file = "A3-Especificaciones.pdf",
        date = 1653760913,
        type = 4
    )
    val fileExtra2 = FilesInTable(
        id = 1002,
        name = "Vídeo de ejemplo",
        description = "Vídeo de la ciudad de Seúl a gran velocidad.",
        file = "seoul.mp4",
        date = 1653660913,
        type = 1
    )
    val fileExtra3 = FilesInTable(
        id = 1003,
        name = "Montaje en Ford Valencia",
        description = "Varios operarios trabajando en el montaje de un vehículo de la marca Ford en la planta de Valencia",
        file = "ford.jpeg",
        date = 1653660813,
        type = 0
    )
    val fileExtra4 = FilesInTable(
        id = 1004,
        name = "Fotografía de oficina",
        description = "Fotografía tomada con las SmartGlasses de Realwear en la oficina de RLE International Iberia en Barcelona.",
        file = "JPEG_1_1_user_6709210335990447830.jpg",
        type = 0,
        date = 1653760813
    )
    val fileExtra5 = FilesInTable(
        id = 1005,
        name = "Edificio Conata",
        description = "Edificio sede de las oficinas de RLE International Iberia en Barcelona.",
        file = "EdificioConata.jpg",
        type = 0,
        date = 1653761813
    )
    val fileExtra6 = FilesInTable(
        id = 1006,
        name = "Piano Melody",
        description = "Prueba de reproducción de sonido",
        file = "piano-melody.wav",
        type = 2,
        date = 1653856775
    )


}