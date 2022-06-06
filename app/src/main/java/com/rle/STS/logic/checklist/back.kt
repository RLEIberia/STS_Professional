package com.rle.STS.logic.checklist

import com.rle.STS.viewModel.ChecklistViewModel

fun back(
    checklistViewModel: ChecklistViewModel
) {

    var viewPosition: Int = 0
    var stepPosition: Int

    //TODO - Sólo lógica - Cambiar de dónde viene todo - ViewModel

    if(viewPosition > 0) viewPosition--
    else backStep()

}