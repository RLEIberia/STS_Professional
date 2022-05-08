package com.rle.STS.model.JSON.checklistStructure

import com.rle.myapplication.model.checklistInfo.Info

data class Checklist (
    val generalInfo: Info,
    val checklistData: ChecklistData
)