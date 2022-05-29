package com.rle.STS.screens.documentSelect

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rle.STS.model.BBDD.FilesInTable
import com.rle.STS.repository.DbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DocumentSelectViewModel @Inject constructor(
    private val dbRepository: DbRepository
): ViewModel() {

    private val _documentList: MutableStateFlow<List<FilesInTable>> = MutableStateFlow(emptyList())
    val documentList = _documentList.asStateFlow()

    private val _filteredDocumentList: MutableStateFlow<List<FilesInTable>> =
        MutableStateFlow(emptyList())
    val filteredDocumentList = _filteredDocumentList.asStateFlow()

    init{
        viewModelScope.launch(Dispatchers.IO) {
            dbRepository.getFilesIn().distinctUntilChanged()
                .collect{ listOfDocuments ->
                    if(listOfDocuments.isNullOrEmpty()){
                        Log.d("DOCS", ": Empty list of documents.")
                    } else {
                        _documentList.value = listOfDocuments
                        Log.d("DOCS", "${_documentList.value}")
                    }
            }
        }
    }

    fun filterDocuments(type: List<Int>){
        viewModelScope.launch(Dispatchers.IO) {
            _filteredDocumentList.value = _documentList.value.filter { document-> document.type in type }
            Log.d("FILTER", ": ${_filteredDocumentList.value}")
        }
    }

}