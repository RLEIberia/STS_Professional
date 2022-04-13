package com.rle.STS.ui.Screens.ChecklistScreens.Data

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rle.STS.ui.Screens.ChecklistScreens.CheckListStepViewModel
import com.rle.STS.ui.widgets.BottomButtons

@Composable
fun TextScreen(title: String, description: String = "", stepViewModel: CheckListStepViewModel) {

    Column() {

        Spacer(modifier = Modifier.weight(1f))

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

        Spacer(modifier = Modifier.weight(1f))

        Spacer(modifier = Modifier.height(10.dp))

        val checkListPosition = stepViewModel.getPosition()
        val checkListSize = stepViewModel.getSize()

        BottomButtons(leftFunction = {
            if (checkListPosition.value!! > 0) {
                stepViewModel.setPosition(checkListPosition.value!! - 1)
            }
        }, rightFunction = {
            if (checkListPosition.value!! >= checkListSize.value!! - 1) {
                //Terminar checklist
            } else {
                stepViewModel.setPosition(checkListPosition.value!! + 1)
            }
        }) // Manejar botones desde aqui para cargar siguiente vista correctamente mediante metodo de lectura de JSON

        Spacer(modifier = Modifier.height(10.dp))

    }

}