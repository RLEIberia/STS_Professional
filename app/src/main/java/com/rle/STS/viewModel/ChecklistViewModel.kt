package com.rle.STS.viewModel

import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.rle.STS.items.Constant
import com.rle.STS.model.BBDD.ExecutionsTable
import com.rle.STS.model.BBDD.StepPersistenceTable
import com.rle.STS.model.BBDD.ViewsPersistenceTable
import com.rle.STS.model.JSON.checklistStructure.ChecklistJSON
import com.rle.STS.repository.ChecklistRepository
import com.rle.STS.repository.DbRepository
import com.rle.STS.screens.viewScreens.ViewScreens
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChecklistViewModel @Inject constructor(
    private val checklistRepository: ChecklistRepository,
    private val dbRepository: DbRepository,
    @ApplicationContext context: Context
) : ViewModel() {

    lateinit var tts: TextToSpeech
    val locSpanish = java.util.Locale("es-ES")

    init {
        viewModelScope.launch(Dispatchers.IO) {
            tts = TextToSpeech(context, TextToSpeech.OnInitListener {
                if (it == TextToSpeech.SUCCESS) {
                    tts.setLanguage(locSpanish)
                    tts.setSpeechRate(1.0f)
                }
            })
        }
    }

    private val _ttsOn: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val ttsOn = _ttsOn.asStateFlow()

    private val _checklistJSON: MutableStateFlow<ChecklistJSON> = MutableStateFlow(ChecklistJSON())
    val checklist = _checklistJSON.asStateFlow()

    //TODO Poner a null
    private val _currentStep: MutableStateFlow<Int> = MutableStateFlow(-1)
    val currentStep = _currentStep.asStateFlow()
    private val _currentView: MutableStateFlow<Int> = MutableStateFlow(-1)
    val currentView = _currentView.asStateFlow()

    private val _executionId: MutableStateFlow<Long> = MutableStateFlow(-1)
    val executionId = _executionId.asStateFlow()

    private val _executionDataFlow = _executionId.flatMapLatest { id ->
        if (id.equals(-1)) {
            Log.d("EXEC_FLOW", ": 0")
            emptyFlow()
        } else {
            Log.d("EXEC_FLOW", ": $id")
            dbRepository.getExecutionFlowById(id).distinctUntilChanged()
        }
    }.flowOn(Dispatchers.IO)
    val executionData = _executionDataFlow.asLiveData()

    //Flow query step
    private val _stepPersistenceId: MutableStateFlow<Long> = MutableStateFlow(0)
    val stepPersistenceId = _stepPersistenceId.asStateFlow()

    private val _stepPersistenceFlow = currentStep.flatMapLatest { step ->
        Log.d("STEPFLOW", ": Executed with $step and ${executionId.value}")
        if (step == -1) {
            Log.d("STEP", ": Null")
            emptyFlow()
        } else {
            dbRepository.getFlowCurrentStep(executionId = executionId.value, step = step + 1)
                .distinctUntilChanged()
        }
    }.flowOn(Dispatchers.IO)
    val stepPersistenceFlow = _stepPersistenceFlow.asLiveData()

    private val _viewPersistenceListFlow = stepPersistenceId.flatMapLatest { stepPersistenceId ->
        dbRepository.getCurrentStepViews(stepPersistenceId = stepPersistenceId)
    }.flowOn(Dispatchers.IO)
    val viewPersistenceListFlow = _viewPersistenceListFlow.asLiveData()

    private val _endDialog: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val endDialog = _endDialog.asStateFlow()

    fun hideEndDialog(){
        viewModelScope.launch(Dispatchers.IO) {
            _endDialog.value = false
        }
    }

    fun startChecklistExecution(
        selectedExecutionId: Long?,
        context: Context,
        userId: Int,
        idCkVersion: Int
    ) = viewModelScope.launch(Dispatchers.IO) {

        chargeJsonData(context, dbRepository.getChecklistById(idCkVersion).json)

        if (selectedExecutionId == null) {

            _executionId.value =
                dbRepository.insertExecution(
                    checklistRepository.executionsInit(
                        user_id = userId,
                        id_ck_version = idCkVersion
                    )
                )
            _currentStep.value = 0
            _currentView.value = 0

            _stepPersistenceId.value =
                dbRepository.insertStep(
                    checklistRepository.stepInit(
                        execution_id = _executionId.value,
                        steps = checklist.value.checklistData!!.steps[_currentStep.value]
                    )
                )

            //Views for Step first insert
            dbRepository.inserMutlipleViewPersistence(
                checklistRepository.viewListInit(
                    stepData = checklist.value.checklistData!!.steps[currentStep.value],
                    executionId = _executionId.value,
                    step_persistence_id = _stepPersistenceId.value
                )
            )
        } else {

            _executionId.value = selectedExecutionId
            _currentStep.value = dbRepository.getExecutionById(selectedExecutionId).current_step
            _currentView.value = dbRepository.getExecutionById(selectedExecutionId).current_view

            _stepPersistenceId.value =
                dbRepository.getCurrentStep(
                    executionId = selectedExecutionId,
                    step = checklist.value.checklistData!!.steps[_currentStep.value].idStep
                ).id!!

        }

        checklistRepository.speakTTS(
            audioText = _checklistJSON.value.checklistData!!.steps[_currentStep.value]
                .views[_currentView.value].viewData.audio,
            tts = tts,
            switch = _ttsOn.value,
            delay = Constant.AUDIO_DELAY
        )

    }

    fun next(
        previousExecutionData: ExecutionsTable,
        wait: Long = 0
    ) =
        viewModelScope.launch(Dispatchers.IO) {

            delay(wait)

            if (_currentView.value + 1 < _checklistJSON.value.checklistData!!.steps[_currentStep.value].views.size) {
                _currentView.value = _currentView.value + 1

                dbRepository.updateExecution(
                    checklistRepository.executionUpdate(
                        previousExecutionData = previousExecutionData,
                        current_step = _currentStep.value,
                        current_view = _currentView.value
                    )
                )
            } else {
                _endDialog.value = true
            }

            checklistRepository.speakTTS(
                audioText = _checklistJSON.value.checklistData!!.steps[_currentStep.value]
                    .views[_currentView.value].viewData.audio,
                tts = tts,
                switch = _ttsOn.value,
                delay = Constant.AUDIO_DELAY
            )
        }

    fun back(previousExecutionData: ExecutionsTable) =
        viewModelScope.launch(Dispatchers.IO) {
            if (_currentView.value > 0) {

                _currentView.value = _currentView.value - 1

                dbRepository.updateExecution(
                    checklistRepository.executionUpdate(
                        previousExecutionData = previousExecutionData,
                        current_step = _currentStep.value,
                        current_view = _currentView.value
                    )
                )
            } else {
//                if(_currentStep.value==0){}
//                else {
//                    _currentView.value = 0
//                    _currentStep.value = _currentStep.value - 1
//                    _currentView.value =
//                        checklist.value.checklistData!!.steps[_currentStep.value].views.size - 1
//                }
            }

            checklistRepository.speakTTS(
                audioText = _checklistJSON.value.checklistData!!.steps[_currentStep.value]
                    .views[_currentView.value].viewData.audio,
                tts = tts,
                switch = _ttsOn.value,
                delay = Constant.AUDIO_DELAY
            )

        }

    fun centerButton(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {

            when (_checklistJSON.value.checklistData!!.steps[currentStep.value]
                .views[currentView.value].viewType) {

                ViewScreens.IM1.name, ViewScreens.IM2.name, ViewScreens.IM3.name ->
                    checklistRepository.openImage(
                        context = context,
                        fileName = _checklistJSON.value.checklistData!!.steps[currentStep.value]
                            .views[currentView.value].viewData.files[0].file
                    )
                ViewScreens.VD1.name ->
                    checklistRepository.openVideo(
                        context = context,
                        fileName = _checklistJSON.value.checklistData!!.steps[currentStep.value]
                            .views[currentView.value].viewData.files[0].file
                    )
            }
        }
    }

    private suspend fun chargeJsonData(context: Context, fileName: String) {

        val jsonData = checklistRepository.getJson(
            context = context,
            fileName = fileName
        )

        _checklistJSON.value = checklistRepository.extractChecklist(
            jsonChecklist = jsonData
        )
    }

    fun viewUpdate(
        previousViewData: ViewsPersistenceTable,
        result: String? = null,
        extra_data: String? = null,
        extra_file: String? = null
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("VIEW_UPDATE", previousViewData.toString())

            dbRepository.updateViewPersistence(
                checklistRepository.viewUpdate(
                    previousViewData = previousViewData,
                    result = result,
                    extra_data = extra_data,
                    extra_file = extra_file
                )
            )
        }
    }

    fun stepUpdate(previousStepData: StepPersistenceTable,
                   result_code: Int? = null,
                   finished: Boolean? = null,
                   last_iteration_check: Boolean? = null,
                   next_step_id: Long? = null
    ){
        viewModelScope.launch(Dispatchers.IO) {
            dbRepository.updateStep(
                checklistRepository.stepUpdate(
                    previousStepData = previousStepData,
                    result_code = result_code,
                    finished = finished,
                    last_iteration_check = last_iteration_check,
                    next_step_id = next_step_id
                )
            )
        }
    }

    fun buttonSpeak() {
        viewModelScope.launch(Dispatchers.IO) {
            checklistRepository.speakTTS(
                audioText = _checklistJSON.value.checklistData!!.steps[_currentStep.value]
                    .views[_currentView.value].viewData.audio,
                tts = tts,
                switch = true,
                delay = Constant.AUDIO_DELAY
            )
        }
    }

    fun ttsSwitch(){
        viewModelScope.launch(Dispatchers.IO) {
            _ttsOn.value = !_ttsOn.value
        }
    }
}
