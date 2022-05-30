package com.rle.STS.screens.state

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rle.STS.model.BBDD.ExecutionsTable
import com.rle.STS.repository.DbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StateViewModel @Inject constructor(
    private val dbRepository: DbRepository
): ViewModel(){

    private val _executionList: MutableStateFlow<List<ExecutionsTable>> =
        MutableStateFlow(emptyList())
    val executionList = _executionList.asStateFlow()

    private val _index: MutableStateFlow<Int> = MutableStateFlow(0)
    val index = _index.asStateFlow()

    init{
        viewModelScope.launch(Dispatchers.IO) {
            dbRepository.getExecutions().distinctUntilChanged()
                .collect(){ listOfExecutions ->
                    _executionList.value = listOfExecutions
                }
        }
    }

    fun indexMove(value: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _index.value = _index.value+value
        }
    }

    fun clearExecutions(){
        viewModelScope.launch(Dispatchers.IO) {
            dbRepository.deleteAllExecutions()
        }
    }



}