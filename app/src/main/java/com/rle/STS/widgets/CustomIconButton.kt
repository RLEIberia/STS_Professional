package com.rle.STS.widgets

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.rle.STS.R
import com.rle.STS.ui.theme.buttonsColor
import com.rle.STS.ui.theme.buttonsTextBorderColor
import com.rle.STS.ui.theme.buttonsTextColor

//Boton preparado con estilo de la aplicacion
@Composable
fun CustomIconButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    buttonColor: Color = buttonsColor,
    textColor: Color = buttonsTextColor,
    textSize: Float = 30.0f,
    borderColor: Color = buttonsTextBorderColor,
    buttonSize: Int = 110,
    enabled: Boolean = true,
    icon: Int = R.drawable.call_icon

) {

    Card(
        elevation = 10.dp,
        shape = RoundedCornerShape(10)
        //color = Color.Black.copy(alpha = 0f)
    ) {
        Column(
            modifier = Modifier
                .background(buttonColor)
                .padding(start = 10.dp, end = 10.dp, top = 15.dp, bottom = 15.dp)
                .width(buttonSize.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier
                    .size(100.dp),
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = Color.White,
            )

            XMLText(text = text, onClick = { onClick() }, enabled = enabled)
        }

    }
}