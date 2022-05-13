package com.rle.STS.repository

import android.content.Context
import com.rle.STS.logic.json.extractChecklistData
import com.rle.STS.model.JSON.checklistStructure.Checklist
import com.rle.STS.utils.GetJsonDataFromAsset
import javax.inject.Inject


class ChecklistRepository @Inject constructor(){

    //Info
    suspend fun getJson(context: Context, fileName: String): String? = GetJsonDataFromAsset(context = context, fileName = fileName)
    suspend fun extractChecklist (jsonChecklist: String?): Checklist = extractChecklistData(jsonChecklist)

}