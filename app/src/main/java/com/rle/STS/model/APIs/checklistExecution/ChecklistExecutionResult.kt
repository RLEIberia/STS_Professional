package com.rle.STS.model.APIs.checklistExecution

import java.util.*

data class ChecklistExecutionResult(
    val execution_id: String,
    val ck_version_id: Int,
    val ck_id: Int,
    val project_id: Int,
    val user: Int,
    val created_at: Long,
    val updated_at: Long,
    val execution: ArrayList<ResultExecution>
)
