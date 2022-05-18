package com.rle.STS.logic.json

import com.google.gson.Gson
import com.rle.STS.model.JSON.checklistStructure.ChecklistJSON

fun extractChecklistData(jsonChecklist: String?): ChecklistJSON {
    return Gson().fromJson(
        jsonChecklist,
        ChecklistJSON::class.java
    )
}