package com.rle.STS.logic.json

import com.google.gson.Gson
import com.rle.STS.model.JSON.checklistStructure.Checklist

fun extractChecklist(jsonChecklist: String?): Checklist {

    return Gson().fromJson(
        jsonChecklist,
        Checklist::class.java
    )

}