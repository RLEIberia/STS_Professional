package com.rle.STS.ui.Screens.ChecklistScreens.Result

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rle.STS.ui.Screens.ChecklistScreens.CheckListStepViewModel
import com.rle.STS.ui.theme.cardsColor
import com.rle.STS.ui.widgets.defaultStepBottomButtons

@Composable
fun MultiOptionScreen(
    option1: String,
    option2: String,
    option3: String = "",
    option4: String = "",
    stepViewModel: CheckListStepViewModel,
    nextType: Int
) {
    val options = listOf(option1, option2, option3, option4)
    val validOptions = arrayListOf<String>()
    val optionSelected = remember { mutableStateListOf(false, false, false, false) }

    for (option in options){
        if (option != ""){
            validOptions.add(option)
        }
    }

    Column() {

        Spacer(modifier = Modifier.height(3.dp))

        for (i in 1..validOptions.size) {
            Row(
                Modifier.fillMaxWidth()
            ) {

                Spacer(modifier = Modifier.width(10.dp))

                Log.d("TEST", i.toString())
                Log.d("TEST", optionSelected[i - 1].toString())

                if (optionSelected[i - 1]) {

                    Box(
                        modifier = Modifier
                            .weight(1.1f)
                    ) {
                        CardView(
                            text = "O" + i,
                            clickable = true,
                            onListClick = {
                                for (j in 0..(optionSelected.size - 1)) {
                                    optionSelected[j] = false
                                }
                                optionSelected[i - 1] = true
                                Log.d("TEST", optionSelected[i - 1].toString())
                            },
                            backgroundColor = Color.Green
                        )
                    }
                    Box(
                        modifier = Modifier
                            .weight(8.9f)
                    ) {
                        CardView(
                            text = validOptions[i - 1],
                            clickable = false,
                            backgroundColor = Color.Green
                        )
                    }
                } else {
                    Box(
                        modifier = Modifier.weight(1.1f)
                    ) {
                        CardView(
                            text = "O" + i,
                            clickable = true,
                            onListClick = {
                                for (j in 0..(optionSelected.size - 1)) {
                                    optionSelected[j] = false
                                }
                                optionSelected[i - 1] = true
                                Log.d("TEST", optionSelected[i - 1].toString())
                            },
                        )
                    }
                    Box(modifier = Modifier.weight(8.9f)) {
                        CardView(
                            text = validOptions[i - 1],
                            clickable = false,
                        )
                    }
                }
                Spacer(modifier = Modifier.width(10.dp))

            }
        }

        Spacer(modifier = Modifier.weight(1f))

        val oneSelected =
            optionSelected[0] || optionSelected[1] || optionSelected[2] || optionSelected[3]
        if (oneSelected) {
            defaultStepBottomButtons(stepViewModel, hasValue = true, nextType = nextType)
        } else {
            defaultStepBottomButtons(stepViewModel, hasValue = false, nextType = nextType)
        }

        Spacer(modifier = Modifier.height(10.dp))

    }

}


//Cards para mostrar los proyectos o checklists
@Composable
fun CardView(
    text: String,
    clickable: Boolean,
    onListClick: () -> Unit = {},
    selected: Boolean = false,
    backgroundColor: Color = cardsColor
) {

    Card(
        shape = RoundedCornerShape(8.dp),
        backgroundColor = backgroundColor,
        elevation = 8.dp,
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth()
                .padding(start = 5.dp, end = 5.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (clickable) {
                Text(
                    text = text,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .semantics { this.contentDescription = text }
                        .clickable(clickable, onClick = onListClick),
                )
                //XMLTextView(text = text, onClick = { onListClick(text) })
            } else {
                Text(
                    text = text,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }

}