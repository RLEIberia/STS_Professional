package com.rle.STS.screens.checklist

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.text.intl.Locale
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rle.STS.model.BBDD.ExecutionsTable
import com.rle.STS.model.BBDD.StepPersistenceTable
import com.rle.STS.model.BBDD.ViewsPersistenceTable
import com.rle.STS.model.JSON.checklistStructure.ChecklistJSON
import com.rle.STS.model.extra.ChecklistPosition
import com.rle.STS.repository.ChecklistRepository
import com.rle.STS.repository.DbRepository
import com.rle.STS.screens.viewScreens.ViewScreens
import com.rle.STS.utils.getCurrentUnix
import com.rle.STS.utils.tts
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.io.File
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ChecklistViewModel @Inject constructor(
    private val checklistRepository: ChecklistRepository,
    private val dbRepository: DbRepository,
    @ApplicationContext context: Context
) : ViewModel() {

    //TODO Cambiar a que la posición dependa de la base de datos

    lateinit var tts: TextToSpeech
    val delay = 300L
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

    private val _ttsOn: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val ttsOn = _ttsOn.asStateFlow()

    private val _checklistJSON: MutableStateFlow<ChecklistJSON> = MutableStateFlow(ChecklistJSON())
    val checklist = _checklistJSON.asStateFlow()

    //Position
    private val _currentStep: MutableStateFlow<Int> = MutableStateFlow(-1)
    val currentStep = _currentStep.asStateFlow()
    private val _currentView: MutableStateFlow<Int> = MutableStateFlow(-1)
    val currentView = _currentView.asStateFlow()

    private val _executionId: MutableStateFlow<Long> = MutableStateFlow(-1)
    val executionId = _executionId.asStateFlow()

    private val _executionDataFlow = _executionId.flatMapLatest { id ->
        if (id.equals(0)) {
            Log.d("EXEC_FLOW", ": 0")
            emptyFlow()
        } else {
            Log.d("EXEC_FLOW", ": $id")
            dbRepository.getExecutionFlowById(id).distinctUntilChanged()
        }
    }.flowOn(Dispatchers.IO)
    val executionData = _executionDataFlow.asLiveData()

    //Flow query step

    private val _stepPersistenceId: MutableStateFlow<Long> =
        MutableStateFlow(0)
    val stepPersistenceId = _stepPersistenceId.asStateFlow()

    private val _stepPersistenceFlow = currentStep.flatMapLatest { step ->
        Log.d("STEPFLOW", ": Executed with $step and ${executionId.value}")
        if (step == -1) {
            Log.d("STEP", ": Null")
            emptyFlow()
        } else {
            dbRepository.getCurrentStep(executionId = executionId.value, step = step + 1)
                .distinctUntilChanged()
        }
    }.flowOn(Dispatchers.IO)
    val stepPersistenceFlow = _stepPersistenceFlow.asLiveData()

    private val _viewPersistenceListFlow = stepPersistenceId.flatMapLatest { stepPersistenceId ->
        dbRepository.getCurrentStepViews(stepPersistenceId = stepPersistenceId)
    }.flowOn(Dispatchers.IO)
    val viewPersistenceListFlow = _viewPersistenceListFlow.asLiveData()

    //Start
    fun startChecklistExecution(
        selectedExecutionId: Int,
        context: Context,
        fileName: String,
        userId: Int,
        idCkVersion: Int
    ) = viewModelScope.launch(Dispatchers.IO) {

        chargeJsonData(context, fileName)

        Log.d("START_CHECKLIST", ": SelectedExe $selectedExecutionId")

        if (selectedExecutionId == 0) {

            Log.d("SELECTED_EXE", ": 0")

            //Execution first insert
            _executionId.value =
                dbRepository.insertExecution(
                    checklistRepository.executionsInit(
                        user_id = userId,
                        id_ck_version = idCkVersion
                    )
                )
            _currentStep.value = 0
            _currentView.value = 0
            Log.d("EXE_ID", ": ${_executionId.value}")
            //Step first insert

            _stepPersistenceId.value =
                dbRepository.insertStep(
                    checklistRepository.stepInit(
                        execution_id = _executionId.value,
                        steps = checklist.value.checklistData!!.steps[currentStep.value]
                    )
                )
            Log.d("STEP_ID", ": ${_stepPersistenceId.value}")

            //Views for Step first insert
            dbRepository.inserMutlipleViewPersistence(
                checklistRepository.viewListInit(
                    stepData = checklist.value.checklistData!!.steps[currentStep.value],
                    executionId = _executionId.value,
                    step_persistence_id = _stepPersistenceId.value
                )
            )
        } else {
            //TODO - Cargar lo que ya haya e ir al paso que toca
            //Los pasos podrían venir de esto directamente y nos cargamos los otros dos campos
        }

        if (_ttsOn.value) {
            try {
                delay(delay)
                checklistRepository.speakTTS(
                    _checklistJSON.value.checklistData!!.steps[_currentStep.value].views[_currentView.value].viewData.audio,
                    tts
                )
            } catch (e: Exception) {
                Log.d("SPEAK", "AUDIO NO PRESENTE")
            }
        }
    }


    fun next() =
        viewModelScope.launch(Dispatchers.IO) {
            if (_currentView.value + 1 < _checklistJSON.value.checklistData!!.steps[_currentStep.value].views.size) {
                _currentView.value = _currentView.value + 1
                Log.d("NEXT", _currentView.value.toString())

            } else {

            }
            if (_ttsOn.value) {
                try {
                    tts.stop()
                    delay(delay)
                    checklistRepository.speakTTS(
                        _checklistJSON.value.checklistData!!.steps[_currentStep.value].views[_currentView.value].viewData.audio,
                        tts
                    )
                } catch (e: Exception) {
                    Log.d("SPEAK", "AUDIO NO PRESENTE")
                }
            }
            Log.d("POS_", _currentView.value.toString())
        }

    fun back() =
        viewModelScope.launch(Dispatchers.IO) {
            if (_currentView.value > 0) {
                _currentView.value = _currentView.value - 1
                Log.d("BACK", _currentView.value.toString())
            } else {
                if(_currentStep.value==0){}
                else {
                    _currentView.value = 0
                    _currentStep.value = _currentStep.value - 1
                    _currentView.value =
                        checklist.value.checklistData!!.steps[_currentStep.value].views.size - 1
                }

            }
            if (_ttsOn.value) {
                try {
                    tts.stop()
                    delay(delay)
                    checklistRepository.speakTTS(
                        _checklistJSON.value.checklistData!!.steps[_currentStep.value].views[_currentView.value].viewData.audio,
                        tts
                    )
                } catch (e: Exception) {
                    Log.d("SPEAK", "AUDIO NO PRESENTE")
                }
            }
            Log.d("POS_", _currentView.value.toString())
        }

    fun centerButton(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {

            when (_checklistJSON.value.checklistData!!.steps[currentStep.value].views[currentView.value].viewType) {

                ViewScreens.IM1.name, ViewScreens.IM2.name, ViewScreens.IM3.name ->
                    checklistRepository.openImage(
                        context = context,
                        fileName = _checklistJSON.value.checklistData!!.steps[currentStep.value].views[currentView.value].viewData.files[0].file
                    )

                ViewScreens.VD1.name ->
                    checklistRepository.openVideo(
                        context = context,
                        fileName = _checklistJSON.value.checklistData!!.steps[currentStep.value].views[currentView.value].viewData.files[0].file
                    )

            }

        }

    }

    private suspend fun chargeJsonData(context: Context, fileName: String) {
        //Charge JsonData
        val jsonData = checklistRepository.getJson(
            context = context,
            fileName = fileName
        )
        Log.d("JSON", jsonData.toString())
        _checklistJSON.value = checklistRepository.extractChecklist(
            jsonChecklist = jsonData
        )
        Log.d("CKVAL", _checklistJSON.value.toString())
        //            val jsonData = GetJsonDataFromAsset(context = context, fileName)
        //            _checklistData.value = com.rle.STS.logic.json.extractChecklist(jsonData)
//        Log.d(
//            "CKVIEWMODEL",
//            checklist.value.checklistData!!.steps[_currentStep.value].views[_currentView.value].viewType
//        )
    }

    fun viewUpdate(previousViewData: ViewsPersistenceTable, result: String) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("VIEW_UPDATE", previousViewData.toString())
            Log.d("RESULT", result)

            dbRepository.updateViewPersistence(
                checklistRepository.viewUpdate(
                    previousViewData = previousViewData,
                    result = result
                )
            )
        }
    }


    fun buttonSpeak() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("BUTTON_AUDIO", ": Pressed.")
            try {
                delay(delay)
                checklistRepository.speakTTS(
                    audioText = _checklistJSON.value.checklistData!!.steps[_currentStep.value].views[_currentView.value].viewData.audio,
                    tts = tts
                )
            } catch (e: Exception) {
                Log.d("SPEAK", "AUDIO NO PRESENTE")
            }
        }
    }

    fun ttsSwitch(){
        viewModelScope.launch(Dispatchers.IO) {
            _ttsOn.value = !_ttsOn.value
        }
    }
}
