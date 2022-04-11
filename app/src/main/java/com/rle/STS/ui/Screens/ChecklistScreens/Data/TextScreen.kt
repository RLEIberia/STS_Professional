package com.rle.STS.ui.Screens.ChecklistScreens.Data

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rle.STS.ui.widgets.BottomButtons


@Composable
fun TextScreen(title : String, description : String = "") {

    Column() {

        Row() {
            Spacer(modifier = Modifier.weight(1f))
            if (description != "") { // Tipo descripcion
                Text(text = title)
                Spacer(modifier = Modifier.weight(1f))
                Text(text = description)
            }else { // Tipo titulo
                Text(text = title)
            }
            Spacer(modifier = Modifier.weight(1f))
        }

    }



}