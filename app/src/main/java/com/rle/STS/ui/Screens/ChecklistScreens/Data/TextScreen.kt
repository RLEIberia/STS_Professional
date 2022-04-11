package com.rle.STS.ui.Screens.ChecklistScreens.Data

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rle.STS.ui.widgets.BottomButtons


@Composable
fun TextScreen(title: String, description: String = "") {

    Row() {

        Spacer(modifier = Modifier.weight(1f))

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            if (description != "") { // Tipo descripcion
                Text(text = title, fontSize = 40.sp)
                Spacer(modifier = Modifier.height(30.dp))
                Text(text = description, fontSize = 25.sp)
            } else { // Tipo titulo
                Text(text = title,  fontSize = 50.sp)
            }

        }

        Spacer(modifier = Modifier.weight(1f))
    }

}