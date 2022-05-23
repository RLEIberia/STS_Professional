package com.rle.STS.widgets

import android.graphics.drawable.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.rle.STS.R
import com.rle.STS.screens.checklist.ChecklistViewModel

@Composable
fun BottomBarChecklist(
    modifier: Modifier = Modifier,
    checklistViewModel: ChecklistViewModel = hiltViewModel(),
    backOnClick: () -> Unit,
    centerActive: Boolean,
    centerText: String,
    centerIcon: Int = R.drawable.options,
    centerOnClick: () -> Unit,
    rightActive: Boolean,
    nextOnClick: () -> Unit,
    centerColor: Color
){

    val position = checklistViewModel.currentPosition.collectAsState().value

    BottomBar(
        modifierBottomBar = modifier,
        leftActive = !(position.step == 0 && position.view == 0),
        leftText = stringResource(id = R.string.back),
        leftIcon = R.drawable.back,
        leftOnClick = backOnClick,
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
        rightOnClick = nextOnClick,
        rightSize = 250
    )
}