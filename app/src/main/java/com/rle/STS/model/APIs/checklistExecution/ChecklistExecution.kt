package com.rle.STS.model.APIs.checklistExecution

data class ChecklistExecution(
    val result: ChecklistExecutionResult,
    val files: ArrayList<ChecklistExecutionFiles>
)
