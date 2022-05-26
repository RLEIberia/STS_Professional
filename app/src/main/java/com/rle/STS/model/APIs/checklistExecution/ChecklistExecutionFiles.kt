package com.rle.STS.model.APIs.checklistExecution

data class ChecklistExecutionFiles(
    val id: String,
    val execution_id: String,
    val type: Int, //1 Imagen, 2 Vídeo, 3 Audio, 4 QR, 5 PDF
    val size: Int
)
