package com.rle.STS.utils

import com.rle.STS.model.BBDD.ExecutionsTable
import com.rle.STS.model.BBDD.StepPersistenceTable
import com.rle.STS.model.BBDD.ViewsPersistenceTable
import com.rle.STS.model.JSON.checklistStructure.Steps
import java.util.*
import kotlin.collections.ArrayList


//INITIALIZE

fun ExecutionsInit(
    user_id: Int = 1,
    id_ck_version: Int = 1,
): ExecutionsTable {

    return ExecutionsTable(
        user_id = user_id,
        id_ck_version = id_ck_version,
        created_at = getCurrentUnix(),
        updated_at = getCurrentUnix(),
        current_step = 0,
        current_view = 0,
        json_result = "",
        state = 0
    )

}

fun StepPersistenceInit(
    execution_id: Long,
    steps: Steps,
    iteration: Int = 1,
    last_step_id: Long = 0,
    next_step_id: Long = -1
): StepPersistenceTable {

    return StepPersistenceTable(
        execution_id = execution_id,
        step_id = steps.idStep,
        step = steps.position,
        result_code = -1,
        iteration = 1,
        finished = false,
        last_iteration_check = true,
        last_step_id = last_step_id,
        next_step_id = next_step_id
    )

}

fun ViewPersistenceListInit(
    stepData: Steps,
    executionId: Long,
    step_persistence_id: Long
): ArrayList<ViewsPersistenceTable> {

    var viewsList: ArrayList<ViewsPersistenceTable> = ArrayList()

    stepData.views.forEach { view ->
        viewsList.add(
            ViewsPersistenceTable(
                execution_id = executionId,
                view_id = view.idView,
                view = view.viewOrder,
                step_persistence_id = step_persistence_id,
                updated_at = getCurrentUnix(),
                result = "",
                extra_data = "",
                extra_file = ""
            )
        )
    }

    return viewsList
}

//UPDATE

fun ExecutionUpdate(
    previousExecutionData: ExecutionsTable,
    current_step: Int? = null,
    current_view: Int? = null,
    json_result: String? = null,
    state: Int? = null
): ExecutionsTable {

    return ExecutionsTable(
        id = previousExecutionData.id,
        user_id = previousExecutionData.user_id,
        id_ck_version = previousExecutionData.id_ck_version,
        created_at = previousExecutionData.created_at,
        updated_at = getCurrentUnix(),
        current_step = current_step ?: previousExecutionData.current_step,
        current_view = current_view ?: previousExecutionData.current_view,
        json_result = json_result ?: previousExecutionData.json_result,
        state = state ?: previousExecutionData.state
    )
}

fun StepUpdate(
    previousStepData: StepPersistenceTable,
    result_code: Int? = null,
    finished: Boolean? = null,
    last_iteration_check: Boolean? = null,
    next_step_id: Long? = null
): StepPersistenceTable {

    return StepPersistenceTable(
        id = previousStepData.id,
        execution_id = previousStepData.execution_id,
        step_id = previousStepData.step_id,
        step = previousStepData.step,
        result_code = result_code ?: previousStepData.result_code,
        iteration = 1,
        finished = finished ?: previousStepData.finished,
        last_iteration_check = last_iteration_check ?: previousStepData.last_iteration_check,
        last_step_id = previousStepData.last_step_id,
        next_step_id = next_step_id ?: previousStepData.next_step_id
    )

}

fun ViewUpdate(
    previousViewData: ViewsPersistenceTable,
    result: String? = null,
    extra_data: String? = null,
    extra_file: String? = null
): ViewsPersistenceTable {

    return ViewsPersistenceTable(
        id = previousViewData.id,
        execution_id = previousViewData.execution_id,
        view_id = previousViewData.view_id,
        view = previousViewData.view,
        step_persistence_id = previousViewData.step_persistence_id,
        updated_at = getCurrentUnix(),
        result = result ?: previousViewData.result,
        extra_data = extra_data ?: previousViewData.extra_data,
        extra_file = extra_file ?: previousViewData.extra_file
    )

}
