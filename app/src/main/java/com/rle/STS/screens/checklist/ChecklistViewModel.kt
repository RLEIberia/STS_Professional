package com.rle.STS.screens.checklist

import android.content.Context
import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rle.STS.model.BBDD.ExecutionsTable
import com.rle.STS.model.JSON.checklistStructure.ChecklistJSON
import com.rle.STS.model.extra.ChecklistPosition
import com.rle.STS.repository.ChecklistRepository
import com.rle.STS.repository.DbRepository
import com.rle.STS.screens.viewScreens.ViewScreens
import com.rle.STS.utils.getCurrentUnix
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ChecklistViewModel @Inject constructor(
    private val checklistRepository: ChecklistRepository,
    private val dbRepository: DbRepository
) : ViewModel() {

    //TODO Cambiar a que la posición dependa de la base de datos

    private val _checklistJSON: MutableStateFlow<ChecklistJSON> = MutableStateFlow(ChecklistJSON())
    val checklist = _checklistJSON.asStateFlow()

    //Position
    private val _currentStep: MutableStateFlow<Int> = MutableStateFlow(0)
    val currentStep = _currentStep.asStateFlow()
    private val _currentView: MutableStateFlow<Int> = MutableStateFlow(0)
    val currentView = _currentView.asStateFlow()

    //Execution data
    //null UUID 00000000-0000-0000-0000-000000000000
    private val _executionId: MutableStateFlow<UUID> =
        MutableStateFlow(UUID.fromString("00000000-0000-0000-0000-000000000000"))
    val executionId = _executionId.asStateFlow()
//    private val _executionData = MutableStateFlow<List<ExecutionsTable>>(emptyList())
//    val executionData = _executionData.asStateFlow()

    private val _executionDataFlow = executionId.flatMapLatest { id ->
        if(id == UUID.fromString("00000000-0000-0000-0000-000000000000")){
            Log.d("EXEC", ": Null")
            emptyFlow()
        }
        else{
            Log.d("EXEC", ": $id")
            dbRepository.getExecutionFlowById(id).distinctUntilChanged()

        }
    }

    val executionData = _executionDataFlow.asLiveData()

    //Start
    fun startChecklistExecution(selectedExecutionId: UUID) =
        viewModelScope.launch(Dispatchers.IO) {
            if (selectedExecutionId == UUID.fromString("00000000-0000-0000-0000-000000000000")) {
                _executionId.value = UUID.randomUUID()
                dbRepository.insertExecution(
                    ExecutionsTable(
                        id = _executionId.value,
                        user_id = 1, //TODO cambiar para que venga por navegación
                        id_ck_version = 1, //TODO cambiar para que venga por navegación
                        created_at = getCurrentUnix(),
                        updated_at = getCurrentUnix(),
                        current_step = 0,
                        current_view = 0,
                        json_result = "",
                        state = 0
                    )
                )
            }
            else {
                //TODO - Cargar lo que ya haya e ir al paso que toca
                //Los pasos podrían venir de esto directamente y nos cargamos los otros dos campos
            }
        }




    fun extractChecklist(fileName: String, context: Context) =
        viewModelScope.launch(Dispatchers.IO) {
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

            Log.d(
                "CKVIEWMODEL",
                checklist.value.checklistData!!.steps[_currentStep.value].views[_currentView.value].viewType
            )

        }

    fun next() =
        viewModelScope.launch(Dispatchers.IO) {
            if (_currentView.value + 1 < _checklistJSON.value.checklistData!!.steps[_currentStep.value].views.size) {
                _currentView.value = _currentView.value + 1
                Log.d("NEXT", _currentView.value.toString())

            } else {

            }
            Log.d("POS_", _currentView.value.toString())
        }

    fun back() =
        viewModelScope.launch(Dispatchers.IO) {
            if(_currentView.value>0){
                _currentView.value = _currentView.value - 1
                Log.d("BACK", _currentView.value.toString())
            } else {

            }
            Log.d("POS_", _currentView.value.toString())


        }

    fun centerButton(context: Context){
        viewModelScope.launch(Dispatchers.IO) {

            when(_checklistJSON.value.checklistData!!.steps[currentStep.value].views[currentView.value].viewType){

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

}

//private val _checkListSize = MutableStateFlow<Int>(0)
//val checkListSize = _checkListSize.asStateFlow()
//
//fun setSize(size: Int) {
//    _checkListSize.value = size
//}
//
///*
//fun getSize(): LiveData<Int> {
//    return _checkListSize
//}
//
//fun setSize(size: Int) {
//    // Do an asynchronous operation to fetch users.
//    _checkListSize.value = size
//}
//*/
//
//private val _checkListPosition = MutableStateFlow<Int>(0)
//val checkListPosition = _checkListPosition.asStateFlow()
//
//fun setPosition(position: Int) {
//    _checkListPosition.value = position
//}
//
//private val _showConfirmDialog = MutableStateFlow<Boolean>(false)
//
//val showConfirmDialog = _showConfirmDialog.asStateFlow()
//
//fun setConfirmDialog(openDialog: Boolean) {
//    _showConfirmDialog.value = openDialog
//}
//
//private val _viewChosen = MutableStateFlow<String>("")
//val viewChosen = _viewChosen.asStateFlow()