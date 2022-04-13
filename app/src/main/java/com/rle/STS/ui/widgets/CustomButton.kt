package com.rle.STS.ui.widgets

import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.rle.STS.R
import com.rle.STS.ui.theme.*

//Boton preparado con estilo de la aplicacion
@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit,
    buttonColor: Color = buttonsColor,
    textColor: Color = buttonsTextColor,
    textSize: Float = 30.0f,
    borderColor: Color = buttonsTextBorderColor,
    buttonSize: Int = 110,
    enabled : Boolean = true
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
        style = MaterialTheme.typography.h4.copy(
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


//Texto del boton preparado con estilo de la aplicacion (Parte interactuable)
@Composable
fun XMLText(
    text: String,
    onClick: (View) -> Unit,
    textColorParameter: Color = textColor,
    textSize: Float = 25.0f,
    borderColor: Color = textBorderColor,
    enabled: Boolean
) {
    val selectedItem = remember { mutableStateOf(0) }

    AndroidView(
        factory = { context ->
            val view = LayoutInflater.from(context)
                .inflate(R.layout.text_layout, null, false)
            val textView = view.findViewById<TextView>(R.id.textView)
            if (enabled){
                textView.setOnClickListener(onClick)
            }
            textView.text = text
            // do whatever you want...
            view // return the view
        },
        update = { view ->
            val textView = view.findViewById<TextView>(R.id.textView)
            if (enabled){
                textView.setOnClickListener(onClick)
            }
            textView.text = text
            // Update the view
        },
        modifier = Modifier.fillMaxWidth()
    )

}
