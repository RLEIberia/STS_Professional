package com.rle.STS.ui.Screens.ChecklistScreens.Result

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rle.STS.ui.widgets.BottomButtons

@Composable
fun MultiOptionScreen() {

    Column() {

        Spacer(modifier = Modifier.weight(1f))


        Row() {
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "Elemento 1 checklist") // ELEMENTO DEL PASO
            Spacer(modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.weight(1f))

    }



}