package com.rle.STS.model.APIs.checklistExecution

data class StepIterationsViews(
    val id: String,
    val view_id: Int,
    val updated_at: Long,
    val result: ArrayList<String>,
    val extra_data: ArrayList<String>,
    val extra_file: ArrayList<String>
)
