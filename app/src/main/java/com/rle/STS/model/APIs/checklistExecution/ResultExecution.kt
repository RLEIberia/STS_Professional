package com.rle.STS.model.APIs.checklistExecution

data class ResultExecution(
    val step_id: Int,
    val step_number: Int,
    val last_step_id: Int,
    val step_iterations: ArrayList<ExecutionStepIterations>
)
