package com.rle.STS.ui.Screens.ChecklistScreens.Result

import android.widget.RadioGroup
import androidx.compose.foundation.layout.*
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.rle.STS.ui.widgets.BottomButtons
import com.rle.STS.ui.widgets.CustomText

@Composable
fun MultiOptionScreen(
    option1: String,
    option2: String,
    option3: String = "",
    option4: String = ""
) {

    val option1Selected = remember { mutableStateOf(false) }
    val option2Selected = remember { mutableStateOf(false) }
    val option3Selected = remember { mutableStateOf(false) }
    val option4Selected = remember { mutableStateOf(false) }

    Row() {
        Spacer(modifier = Modifier.weight(1f))
        RadioButton(
            selected = option1Selected.value,
            onClick = {},
            colors = RadioButtonDefaults.colors(
                selectedColor = Color.Green,
                unselectedColor = Color.Black
            )
        )
        CustomText(text = option1, onClick = {
            option1Selected.value = true
            option2Selected.value = false
            option3Selected.value = false
            option4Selected.value = false
        })
        Spacer(modifier = Modifier.weight(1f))
        RadioButton(
            selected = option2Selected.value,
            onClick = {},
            colors = RadioButtonDefaults.colors(
                selectedColor = Color.Green,
                unselectedColor = Color.Black
            )
        )
        CustomText(text = option2, onClick = {
            option1Selected.value = false
            option2Selected.value = true
            option3Selected.value = false
            option4Selected.value = false
        })
        Spacer(modifier = Modifier.weight(1f))
    }

    if (option3 != "") {
        Spacer(modifier = Modifier.height(40.dp))

        Row() {
            Spacer(modifier = Modifier.weight(1f))
            RadioButton(
                selected = option3Selected.value,
                onClick = {},
                colors = RadioButtonDefaults.colors(
                    selectedColor = Color.Green,
                    unselectedColor = Color.Black
                )
            )
            CustomText(text = option3, onClick = {
                option1Selected.value = false
                option2Selected.value = false
                option3Selected.value = true
                option4Selected.value = false
            })

            Spacer(modifier = Modifier.weight(1f))

            if (option4 != "") {
                RadioButton(
                    selected = option4Selected.value,
                    onClick = {},
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Color.Green,
                        unselectedColor = Color.Black
                    )
                )
                CustomText(text = option4, onClick = {
                    option1Selected.value = false
                    option2Selected.value = false
                    option3Selected.value = false
                    option4Selected.value = true
                })
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }

}