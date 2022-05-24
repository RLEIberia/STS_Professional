package com.rle.STS.screens.viewScreens.attach

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rle.STS.screens.checklist.ChecklistViewModel
import com.rle.STS.screens.viewScreens.utils.DescriptionRow
import com.rle.STS.ui.theme.cardTextColor
import com.rle.STS.widgets.CustomTopIconButton

@Composable
fun TextFieldIntroduce(
    checklistViewModel: ChecklistViewModel,
    pendingText: String = "Pendiente de lectura...",
    finishedText: String = "Lectura finalizada.",
    buttonFunction: () -> Unit,
    buttonText: String,
    buttonColor: Color,
    buttonIcon: Int,
    buttonSize: Int = 180,
    result: String,
    textResultSize: Int = 25,
    textResultWeight: FontWeight = FontWeight.SemiBold

) {

    val currentStep = checklistViewModel.currentStep.collectAsState()
    val currentView = checklistViewModel.currentView.collectAsState()
    val viewData =
        checklistViewModel.checklist.collectAsState().value.checklistData!!.steps[currentStep.value]
            .views[currentView.value].viewData



    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            //Description Row
            DescriptionRow(
                viewData = viewData,
                modifier = Modifier
                    .weight(0.8f)
            )

            //Result Row
            Row(
                modifier = Modifier
                    .weight(1f),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.75f)

                ) {

                    Card(
                        shape = RoundedCornerShape(10),
                        elevation = 5.dp
                    ) {

                        if (result.isEmpty()) {
                            Column(
                                modifier = Modifier
//                                    .padding(5.dp)
                                    .fillMaxWidth()
                                    .fillMaxHeight()
                                    .background(cardTextColor),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.SpaceEvenly
                            ) {

                                Text(
                                    modifier = Modifier
                                        .padding(5.dp),
                                    text = pendingText,
                                    fontSize = 25.sp,
                                    textAlign = TextAlign.Center
                                )
                            }
                        } else {
                            Column(
                                modifier = Modifier
//                                    .padding(5.dp)
                                    .fillMaxWidth()
                                    .fillMaxHeight()
                                    .background(cardTextColor),
                                horizontalAlignment = Alignment.CenterHorizontally,
//                                verticalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        modifier = Modifier
                                            .padding(5.dp),
                                        text = finishedText,
                                        fontSize = 20.sp,
                                        textAlign = TextAlign.Center
                                    )
                                }
                                Row(
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        modifier = Modifier
                                            .padding(5.dp),
                                        text = result,
                                        fontSize = textResultSize.sp,
                                        textAlign = TextAlign.Center,
                                        fontWeight = textResultWeight
                                    )
                                }
                            }
                        }

                    }

                }

                Spacer(
                    modifier = Modifier.fillMaxWidth(0.06f)
                )

                CustomTopIconButton(
                    modifier = Modifier
                        .fillMaxWidth(0.3f),
                    text = buttonText,
                    buttonColor = buttonColor, //Color(0xFF3BCE8E)
                    onClick = buttonFunction, //launcherBarCode.launch(intentBarCode)
                    buttonSize = buttonSize,
                    icon = buttonIcon
                )

            }
        }
    }
}




