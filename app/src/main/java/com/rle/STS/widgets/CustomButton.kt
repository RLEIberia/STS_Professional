package com.rle.STS.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rle.STS.ui.theme.*

//Boton preparado con estilo de la aplicacion
@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    buttonColor: Color = buttonsColor,
    textColor: Color = buttonsTextColor,
    textSize: Float = 30.0f,
    borderColor: Color = buttonsTextBorderColor,
    buttonSize: Int = 110,
    enabled: Boolean = true,
) {

    androidx.compose.material.Surface(
        elevation = 4.dp,
        color = Color.Black.copy(alpha = 0f)
    ) {
        Box(
            modifier = Modifier
                .background(color = buttonColor, shape = RoundedCornerShape(10))
                .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp)
                .width(buttonSize.dp)
        ) {
            /*CustomText(
                text = text,
                onClick = onClick,
                textColorParameter = textColor,
                borderColor = borderColor
            )*/
            XMLText(text = text, onClick = { onClick() }, enabled = enabled)
        }
    }
}

//Texto del boton preparado con estilo de la aplicacion (Parte interactuable)
@Composable
fun CustomText(
    text: String,
    onClick: () -> Unit,
    textColorParameter: Color = textColor,
    textSize: Float = 25.0f,
    borderColor: Color = textBorderColor,
    enabled: Boolean = true
) {
    val context = LocalContext.current

    Text(
        text = text,
        fontSize = textSize.sp,
        fontWeight = FontWeight.Bold,
        color = textColorParameter,
        textAlign = TextAlign.Center,
        style = MaterialTheme
            .typography.h4.copy(
                shadow = Shadow(
                    color = Color.Black,
                    offset = Offset(2f, 2f),
                    blurRadius = 4f
                ),
            ),
        modifier = Modifier
            .clickable(enabled = enabled, onClick = onClick)
        //.border(width = 2.dp, color = borderColor, shape= Shape)
    )

}


