package com.rle.STS.screens.viewScreens.attach

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rle.STS.viewModel.ChecklistViewModel
import com.rle.STS.screens.viewScreens.utils.DescriptionRow
import com.rle.STS.ui.theme.cardTextColor
import com.rle.STS.widgets.CustomButton

@Composable
fun NM1Screen(checklistViewModel: ChecklistViewModel) {

    val currentStep = checklistViewModel.currentStep.collectAsState()
    val currentView = checklistViewModel.currentView.collectAsState()
    val viewPersistence = checklistViewModel.viewPersistenceListFlow.observeAsState(emptyList())
    val viewData = checklistViewModel.checklist.collectAsState().value.checklistData!!
        .steps[currentStep.value].views[currentView.value].viewData

    val value = remember {
        mutableStateOf("")
    }

    val focusRequester = FocusRequester()

    if (!viewPersistence.value.isNullOrEmpty()) {

        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {

                //Description Row
                DescriptionRow(viewData = viewData, modifier = Modifier)

                Row() {
                    Card(
                        shape = RoundedCornerShape(10),
                        elevation = 5.dp
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize(0.5f)
                                .background(cardTextColor),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row() {
                                Text(
                                    modifier = Modifier
                                        .padding(5.dp),
                                    text = "Introduzca el valor numérico:",
                                    fontSize = 25.sp,
                                    textAlign = TextAlign.Center
                                )
                            }
                            Column(
                                modifier = Modifier
                                    .fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                TextField(
                                    value = viewPersistence.value[currentView.value].result,
                                    modifier = Modifier
                                        .focusRequester(focusRequester),
                                    textStyle = MaterialTheme.typography.h5,
                                    onValueChange = {
                                        checklistViewModel.viewUpdate(
                                            previousViewData = viewPersistence.value[currentView.value],
                                            result = it
                                        )
                                        focusRequester.freeFocus()
                                    },
                                    keyboardOptions = KeyboardOptions(
                                        keyboardType = KeyboardType.Text,
                                        imeAction = ImeAction.Done
                                    ),
                                    keyboardActions = KeyboardActions(
                                        onDone = {focusRequester.freeFocus()},
                                        onPrevious = {focusRequester.freeFocus()}
                                    )

                                )
//                                OutlinedTextField(
//                                    value = value.toString(),
//                                    onValueChange = { },
//                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword)
//                                    //KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
//                                )

                            }

                        }

                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    CustomButton(
                        text = "INTRODUCIR",
                        onClick = { focusRequester.requestFocus() },
                        buttonSize = 220
                    )
                    Divider(
                        Modifier. width(5.dp)
                    )
                    CustomButton(
                        text = "LIMPIAR",
                        onClick = { focusRequester.freeFocus() },
                        buttonSize = 220
                    )
                }
            }
        }
    }
}