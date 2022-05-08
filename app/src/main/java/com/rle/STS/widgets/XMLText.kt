package com.rle.STS.widgets

import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView
import com.rle.STS.R
import com.rle.STS.ui.theme.textBorderColor
import com.rle.STS.ui.theme.textColor

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
            if (enabled) {
                textView.setOnClickListener(onClick)
            }
            textView.text = text
            // do whatever you want...
            view // return the view
        },
        update = { view ->
            val textView = view.findViewById<TextView>(R.id.textView)
            if (enabled) {
                textView.setOnClickListener(onClick)
            }
            textView.text = text
            // Update the view
        },
        modifier = Modifier.fillMaxWidth()
    )

}