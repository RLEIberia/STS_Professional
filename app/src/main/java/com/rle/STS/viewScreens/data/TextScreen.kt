package com.rle.STS.viewScreens.data

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rle.STS.screens.checklist.ChecklistViewModel
import com.rle.STS.widgets.defaultStepBottomButtons

@Composable
fun TextScreen(title: String, description: String = "", stepViewModel: ChecklistViewModel) {

    Column() {

        Spacer(modifier = Modifier.weight(1f))

        Row() {

            Spacer(modifier = Modifier.weight(1f))

            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                if (description != "") { // Tipo descripcion
                    Text(text = title, fontSize = 40.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(30.dp))
                    Text(text = description, fontSize = 25.sp, modifier = Modifier.padding(15.dp), textAlign = TextAlign.Center)
                } else { // Tipo titulo
                    Text(text = title,  fontSize = 50.sp)
                }

            }

            Spacer(modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.weight(1f))

        Spacer(modifier = Modifier.height(10.dp))

        defaultStepBottomButtons(stepViewModel)

        Spacer(modifier = Modifier.height(10.dp))

    }

}