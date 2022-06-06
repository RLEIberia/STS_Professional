package com.rle.STS.screens.viewScreens.result

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.rle.STS.items.Constant
import com.rle.STS.viewModel.ChecklistViewModel
import com.rle.STS.screens.viewScreens.utils.DescriptionRow
import com.rle.STS.ui.theme.buttonOkColor
import com.rle.STS.ui.theme.correctAnswer
import com.rle.STS.ui.theme.specialButtonColor
import com.rle.STS.widgets.ListRow

@Composable
fun OP2Screen(
    checklistViewModel: ChecklistViewModel
) {

    val currentStep = checklistViewModel.currentStep.collectAsState()
    val currentView = checklistViewModel.currentView.collectAsState()
    val viewPersistence = checklistViewModel.viewPersistenceListFlow.observeAsState(emptyList())
    val stepPersistence = checklistViewModel.stepPersistenceFlow.observeAsState(emptyList())
    val viewData =
        checklistViewModel.checklist.collectAsState().value.checklistData!!.steps[currentStep.value]
            .views[currentView.value].viewData

    val executionData = checklistViewModel.executionData.observeAsState(emptyList())

    val listState = rememberLazyListState()

    if (!viewPersistence.value.isNullOrEmpty()) {

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

                DescriptionRow(viewData = viewData)

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {

                    LazyColumn(
//                    modifier = Modifier
//                        .padding(10.dp),
                        state = listState
                    ) {
                        itemsIndexed(items = viewData.options) { index, item ->
                            ListRow(
                                buttonLetter = "OP",
                                buttonColor =
                                    when (viewPersistence.value[currentView.value].result) {
                                        Constant.EMPTY_STRING -> specialButtonColor
                                        ((index).toString()) -> buttonOkColor
                                        else -> Color.Gray
                                    },
                                height = 50.dp,
                                rowColor =
                                    when (viewPersistence.value[currentView.value].result) {
                                        Constant.EMPTY_STRING -> Color.White
                                        ((index).toString()) -> correctAnswer
                                        else -> Color.LightGray
                                    },
                                textColor =
                                    when (viewPersistence.value[currentView.value].result) {
                                        Constant.EMPTY_STRING -> Color.Black
                                        ((index).toString()) -> Color.Black
                                        else -> Color.Gray
                                    },
                                number = index,
                                onClick = {
                                    checklistViewModel.viewUpdate(
                                        previousViewData = viewPersistence.value[currentView.value],
                                        result = (index).toString()
                                    )
                                    checklistViewModel.stepUpdate(
                                        previousStepData = stepPersistence.value[0],
                                        result_code = index
                                    )
                                    checklistViewModel.next(
                                        previousExecutionData = executionData.value[0],
                                        wait = 500
                                    )
                                },
                                title = item
                            )
                        }
                    }
                }
            }
        }
    }
}