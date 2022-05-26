package com.rle.STS.model.APIs.checklistExecution

data class ExecutionStepIterations(
    val id: String,
    val result_code: Int,
    val finished: Boolean,
    val iteration: Int,
    val last_iteration: Boolean,
    val created_at: Long,
    val updated_at: Long,
    val next_step_id: Int,
    val views: ArrayList<StepIterationsViews>
)
