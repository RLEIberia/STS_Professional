package com.rle.STS.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.rle.STS.R
import com.rle.STS.screens.checklist.ChecklistViewModel
import com.rle.STS.screens.viewScreens.ViewScreens

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
) {

    val context = LocalContext.current

    val currentStep = checklistViewModel.currentStep.collectAsState()
    val currentView = checklistViewModel.currentView.collectAsState()
    val checklist = checklistViewModel.checklist.collectAsState()

    val centerActiveView = remember {
        mutableStateOf(false)
    }
    val centerTextView = remember {
        mutableStateOf("CENTER")
    }
    val centerIconView = remember {
        mutableStateOf(R.drawable.options)
    }

    if (checklist.value.checklistData != null) {

        when (checklist.value.checklistData!!.steps[currentStep.value].views[currentView.value].viewType) {

            ViewScreens.IM1.name, ViewScreens.IM2.name, ViewScreens.IM3.name -> {
                centerActiveView.value = true
                centerTextView.value = "AMPLIAR"
            }
            ViewScreens.VD1.name -> {
                centerActiveView.value = true
                centerTextView.value = "REPRODUCIR"
            }

            else -> {
                centerActiveView.value = false
            }

        }
    }

    BottomBar(
        modifierBottomBar = modifier,
        leftActive = !(currentStep.value == 0 && currentView.value == 0),
        leftText = stringResource(id = R.string.back),
        leftIcon = R.drawable.back,
        leftOnClick = { checklistViewModel.back() },
        leftSize = 250,
        centerActive = centerActiveView.value,
        centerText = centerTextView.value,
        centerIcon = centerIconView.value,
        centerOnClick = { checklistViewModel.centerButton(context = context) },
        centerColor = centerColor,
        centerSize = 250,
        rightActive = rightActive,
        rightText = stringResource(id = R.string.next),
        rightIcon = R.drawable.next,
        rightOnClick = { checklistViewModel.next() },
        rightSize = 250
    )
}




