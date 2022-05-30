package com.rle.STS.repository

import android.content.Context
import android.speech.tts.TextToSpeech
import com.rle.STS.logic.json.extractChecklistData
import com.rle.STS.model.BBDD.ExecutionsTable
import com.rle.STS.model.BBDD.StepPersistenceTable
import com.rle.STS.model.BBDD.ViewsPersistenceTable
import com.rle.STS.model.JSON.checklistStructure.ChecklistJSON
import com.rle.STS.model.JSON.checklistStructure.Steps
import com.rle.STS.utils.*
import com.rle.STS.utils.checklistUtils.openImage
import com.rle.STS.utils.checklistUtils.openVideo
import com.rle.STS.utils.checklistUtils.openPdf
import com.rle.STS.utils.checklistUtils.speak
import java.util.*
import javax.inject.Inject


class ChecklistRepository @Inject constructor(){

    //Info
    suspend fun getJson(context: Context, fileName: String): String? =
        GetJsonDataFromAsset(context = context, fileName = fileName)
    suspend fun extractChecklist (jsonChecklist: String?): ChecklistJSON =
        extractChecklistData(jsonChecklist)

    //Utils
    suspend fun openImage(fileName: String, context: Context) =
        openImage(file = fileName, context = context)
    suspend fun openVideo(fileName: String, context: Context) =
        openVideo(file = fileName, context = context)

    fun openPdf(fileName: String, context: Context) =
        openPdf(file = fileName, context = context)

    suspend fun speakTTS(audioText: CharSequence, tts: TextToSpeech) = speak(audioText = audioText, tts = tts)

    //Initialize persistence
    fun executionsInit(user_id: Int = 1, id_ck_version: Int = 1) : ExecutionsTable =
        ExecutionsInit(user_id = user_id, id_ck_version = id_ck_version)
    fun stepInit(execution_id: Long, steps: Steps, iteration: Int = 1,
                 last_step_id: Long = 0, next_step_id: Long = -1): StepPersistenceTable =
        StepPersistenceInit(execution_id = execution_id, steps = steps,
            iteration = iteration, last_step_id = last_step_id, next_step_id = next_step_id)
    fun viewListInit(stepData: Steps, executionId: Long, step_persistence_id: Long): ArrayList<ViewsPersistenceTable> =
        ViewPersistenceListInit(stepData = stepData, executionId = executionId, step_persistence_id = step_persistence_id)

    //Update persistence
    fun executionUpdate(
        previousExecutionData: ExecutionsTable,
        current_step: Int? = null,
        current_view: Int? = null,
        json_result: String? = null,
        state: Int? = null
    ): ExecutionsTable =
        ExecutionUpdate(
            previousExecutionData = previousExecutionData,
            current_step = current_step,
            current_view = current_view,
            json_result = json_result,
            state = state
        )

    fun stepUpdate(
        previousStepData: StepPersistenceTable,
        result_code: Int? = null,
        finished: Boolean? = null,
        last_iteration_check: Boolean? = null,
        next_step_id: Long? = null
    ): StepPersistenceTable =
        StepUpdate(
            previousStepData = previousStepData,
            result_code = result_code,
            finished = finished,
            last_iteration_check = last_iteration_check,
            next_step_id = next_step_id
        )

    suspend fun viewUpdate(
        previousViewData: ViewsPersistenceTable,
        result: String? = null,
        extra_data: String? = null,
        extra_file: String? = null
    ): ViewsPersistenceTable =
        ViewUpdate(
            previousViewData = previousViewData,
            result = result,
            extra_data = extra_data,
            extra_file = extra_file
        )

}