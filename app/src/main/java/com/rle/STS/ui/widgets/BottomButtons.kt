package com.rle.STS.ui.widgets

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.rle.STS.R
import com.rle.STS.ui.theme.buttonExtraColor

//VERSION SIN PARAMETROS UNICAMENTE PARA TEST DE PANTALLAS
@Composable
fun BottomButtons() {
    val context = LocalContext.current
    Row(modifier = Modifier.semantics { this.contentDescription = "hf_no_overlay|hf_use_text" }) { // BOTONES
        Spacer(modifier = Modifier.width(20.dp))
        CustomButton(text = stringResource(R.string.ir_atras), onClick = { Toast.makeText(context,"prueba", Toast.LENGTH_LONG).show() }, buttonSize = 150)
        Spacer(modifier = Modifier.weight(1f))
        CustomButton(text = stringResource(R.string.siguiente), onClick = { Toast.makeText(context,"prueba", Toast.LENGTH_LONG).show() }, buttonSize = 150)
        Spacer(modifier = Modifier.width(20.dp))
    }
}


@Composable
fun BottomButtons(leftFunction: () -> Unit, rightFunction: () -> Unit, leftText : String = stringResource(R.string.ir_atras), rightText: String = stringResource(R.string.siguiente), leftVisible: Boolean = true, rightVisible: Boolean = true) {
    Row() { // 2 BOTONES
        Spacer(modifier = Modifier.width(20.dp))
        CustomButton(text = leftText, onClick = leftFunction, visibility = leftVisible, buttonSize = 150)
        Spacer(modifier = Modifier.weight(1f))
        CustomButton(text = rightText, onClick = rightFunction, visibility = rightVisible, buttonSize = 150)
        Spacer(modifier = Modifier.width(20.dp))
    }
}


@Composable
fun BottomButtons(middleText : String, middleFunction: () -> Unit, middleColor: Color = buttonExtraColor, leftText : String = stringResource(R.string.ir_atras), rightText: String = stringResource(R.string.siguiente), leftFunction: () -> Unit = {}, rightFunction: () -> Unit = {}, leftVisible: Boolean = true, rightVisible: Boolean = true) {
    Row() { // 3 BOTONES
        Spacer(modifier = Modifier.width(20.dp))
        CustomButton(text = leftText, onClick = leftFunction, visibility = leftVisible, buttonSize = 150)
        Spacer(modifier = Modifier.weight(1f))
        CustomButton(text = middleText, onClick = middleFunction, buttonColor = middleColor, buttonSize = 150)
        Spacer(modifier = Modifier.weight(1f))
        CustomButton(text = rightText, onClick = rightFunction, visibility = rightVisible, buttonSize = 150)
        Spacer(modifier = Modifier.width(20.dp))
    }
}