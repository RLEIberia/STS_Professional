package com.rle.STS.widgets

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.rle.STS.R
import com.rle.STS.screens.checklist.ChecklistViewModel
import com.rle.STS.ui.theme.buttonExtraColor

//VERSION SIN PARAMETROS UNICAMENTE PARA TEST DE PANTALLAS
@Composable
fun BottomButtons() {
    val context = LocalContext.current
    Row(modifier = Modifier.semantics {
        this.contentDescription = "hf_no_overlay|hf_use_text"
    }) { // BOTONES
        Spacer(modifier = Modifier.width(20.dp))
        CustomButton(
            text = stringResource(R.string.previous),
            onClick = { Toast.makeText(context, "test", Toast.LENGTH_LONG).show() },
            buttonSize = 150
        )
        Spacer(modifier = Modifier.weight(1f))
        CustomButton(
            text = stringResource(R.string.next),
            onClick = { Toast.makeText(context, "test", Toast.LENGTH_LONG).show() },
            buttonSize = 150
        )
        Spacer(modifier = Modifier.width(20.dp))
    }
}


@Composable
fun BottomButtons(
    leftFunction: () -> Unit,
    rightFunction: () -> Unit,
    leftText: String = stringResource(R.string.go_back),
    rightText: String = stringResource(R.string.next),
    leftVisible: Boolean = true,
    rightVisible: Boolean = true
) {
    Row() { // 2 BOTONES
        Spacer(modifier = Modifier.width(20.dp))
        if (leftVisible) {
            CustomButton(
                text = leftText,
                onClick = leftFunction,
                buttonSize = 150
            )
        } else {
            Box(modifier = Modifier.width(190.dp))
        }
        Spacer(modifier = Modifier.weight(1f))
        if (rightVisible) {
            CustomButton(
                text = rightText,
                onClick = rightFunction,
                buttonSize = 150
            )
        } else {
            Box(modifier = Modifier.width(190.dp))
        }
        Spacer(modifier = Modifier.width(20.dp))
    }
}


@Composable
fun BottomButtons(
    middleText: String,
    middleFunction: () -> Unit,
    middleColor: Color = buttonExtraColor,
    middleInteractable: Boolean = true,
    leftText: String = stringResource(R.string.go_back),
    rightText: String = stringResource(R.string.next),
    leftFunction: () -> Unit = {},
    rightFunction: () -> Unit = {},
    leftVisible: Boolean = true,
    rightVisible: Boolean = true
) {
    Row() { // 3 BOTONES
        Spacer(modifier = Modifier.width(20.dp))
        if (leftVisible) {
            CustomButton(
                text = leftText,
                onClick = leftFunction,
                buttonSize = 150
            )
        } else {
            Box(modifier = Modifier.width(170.dp))
        }
        Spacer(modifier = Modifier.weight(1f))
        CustomButton(
            text = middleText,
            onClick = middleFunction,
            buttonColor = middleColor,
            buttonSize = 150,
            enabled = middleInteractable,
        )
        Spacer(modifier = Modifier.weight(1f))
        if (rightVisible) {
            CustomButton(
                text = rightText,
                onClick = rightFunction,
                buttonSize = 150
            )
        } else {
            Box(modifier = Modifier.width(170.dp))
        }
        Spacer(modifier = Modifier.width(20.dp))
    }
}


@Composable
fun defaultStepBottomButtons(stepViewModel: ChecklistViewModel, hasValue: Boolean = true, nextType: Int = 1){

    val checkListPosition = stepViewModel.checkListPosition.collectAsState()
    val checkListSize = stepViewModel.checkListSize.collectAsState()

    BottomButtons(leftFunction = {
        if (checkListPosition.value > 0) {
            stepViewModel.setPosition(checkListPosition.value - 1)
        }
    }, rightFunction = {
        if (hasValue || nextType == 0) {
            if (checkListPosition.value >= checkListSize.value - 1) {
                //TODO: Terminar checklist
            } else {
                stepViewModel.setPosition(checkListPosition.value + 1)
            }
        } else {
            if (nextType == 1){
                stepViewModel.setConfirmDialog(true)
            } else {

            }
        }
    }) // Manejar botones desde aqui para cargar siguiente vista correctamente mediante metodo de lectura de JSON
}