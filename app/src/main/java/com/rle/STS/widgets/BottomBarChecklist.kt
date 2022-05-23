package com.rle.STS.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.rle.STS.R
import com.rle.STS.logic.checklist.back
import com.rle.STS.screens.checklist.ChecklistViewModel

@Composable
fun BottomBarChecklist(
    modifier: Modifier = Modifier,
    checklistViewModel: ChecklistViewModel,
    backOnClick: () -> Unit,
    centerActive: Boolean,
    centerText: String,
    centerIcon: Int = R.drawable.options,
    centerOnClick: () -> Unit,
    rightActive: Boolean,
    centerColor: Color
){

    val currentStep = checklistViewModel.currentStep.collectAsState()
    val currentView = checklistViewModel.currentView.collectAsState()
    val checklist = checklistViewModel.checklist.collectAsState().value

    BottomBar(
        modifierBottomBar = modifier,
        leftActive = !(currentStep.value == 0 && currentView.value == 0),
        leftText = stringResource(id = R.string.back),
        leftIcon = R.drawable.back,
        leftOnClick = {  },
        leftSize = 250,
        centerActive = centerActive,
        centerText = centerText,
        centerIcon = centerIcon,
        centerOnClick = centerOnClick,
        centerColor = centerColor,
        centerSize = 250,
        rightActive = rightActive,
        rightText = stringResource(id = R.string.next),
        rightIcon = R.drawable.next,
        rightOnClick = { checklistViewModel.next() },
        rightSize = 250
    )
}

