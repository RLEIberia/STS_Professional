package com.rle.STS.screens.documentSelect

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.rle.STS.R
import com.rle.STS.model.BBDD.FilesInTable
import com.rle.STS.navigation.STSScreens
import com.rle.STS.screens.Drawer
import com.rle.STS.ui.theme.buttonOkColor
import com.rle.STS.ui.theme.buttonStop
import com.rle.STS.ui.theme.grayedButton
import com.rle.STS.ui.theme.specialButtonColor
import com.rle.STS.utils.checklistUtils.openAudio
import com.rle.STS.utils.checklistUtils.openImage
import com.rle.STS.utils.checklistUtils.openPdf
import com.rle.STS.utils.checklistUtils.openVideo
import com.rle.STS.widgets.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.*

@Composable
fun DocumentSelectScreen(
    navController: NavController,
    documentSelectViewModel: DocumentSelectViewModel
) {

    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    val context = LocalContext.current
    val buttonWidth = 250

    val filteredDocumentList = documentSelectViewModel.filteredDocumentList.collectAsState()
    val documentFilter = remember {
        mutableListOf(Int)
    }

    val selectedFilter = remember {
        mutableStateOf(-1)
    }

    val filterDialogState = remember {
        mutableStateOf(false)
    }

    LaunchedEffect(
        key1 = true,
        block = { documentSelectViewModel.filterDocuments(Arrays.asList(0, 1, 2, 3, 4)) }
    )


    if (filterDialogState.value) {
        filterDialog(
            documentSelectViewModel = documentSelectViewModel,
            filterDialogState = filterDialogState,
            scope = scope,
            selectedFilter = selectedFilter
        )
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            SimpleTopBar(
                scope = scope,
                scaffoldState = scaffoldState,
                text = "Documentos"
            )
        },
        drawerContent = {
            Drawer(scaffoldState = scaffoldState, scope = scope)
        },
        bottomBar = {
            val indexCounter = remember {
                mutableStateOf(0)
            }
            BottomBar(
                modifierBottomBar = Modifier,
                leftActive = (indexCounter.value - 4 >= 0),
                leftText = context.getString(R.string.up),
                leftSize = buttonWidth,
                leftOnClick = {
                    indexCounter.value = indexCounter.value - 4
                    scope.launch {
                        listState.animateScrollToItem(
                            index = indexCounter.value
                        )
                    }
                },
                centerActive = true,
                centerText = "FILTRO",
                centerSize = buttonWidth,
                centerColor = specialButtonColor,
                centerOnClick = { filterDialogState.value = true },
                rightActive = (indexCounter.value + 4 < filteredDocumentList.value.size),
                rightText = context.getString(R.string.down),
                rightSize = buttonWidth,
                rightOnClick = {
                    indexCounter.value = indexCounter.value + 4
                    scope.launch {
                        listState.animateScrollToItem(
                            index = indexCounter.value
                        )
                    }
                }
            )
        }

    ) {

        if (filteredDocumentList.value.isNotEmpty()) {

            Surface(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
            ) {
                LazyColumn(
                    state = listState
                ) {
                    itemsIndexed(items = filteredDocumentList.value) { index, item ->
                        ListRow(
                            buttonLetter = "FL",
                            buttonColor = specialButtonColor,
                            rowColor = Color.White,
                            textColor = Color.Black,
                            number = index,
                            title = item.name,
                            dateTimeStamp = item.date,
                            onClick = {
                                when (item.type) {
                                    0 -> openImage(file = item.file, context = context)
                                    1 -> openVideo(file = item.file, context = context)
                                    2 -> openAudio(file = item.file, context = context)
                                    3 -> {}
                                    4 -> openPdf(file = item.file, context = context)
                                    else -> {}
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun filterDialog(
    documentSelectViewModel: DocumentSelectViewModel,
    selectedFilter: MutableState<Int>,
    filterDialogState: MutableState<Boolean>,
    scope: CoroutineScope
) {

    Dialog(
        onDismissRequest = { filterDialogState.value = false },
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
        ),
    ) {

        Scaffold(
            modifier = Modifier
                .fillMaxWidth(0.5f),
            topBar = {
                VerySimpleTopBar(
                    text = "FILTRO"
                )
            },
            bottomBar = {
                BottomBar(
                    leftExists = false,
                    rightExists = false,
                    centerText = "VOLVER",
                    centerSize = 350,
                    centerOnClick = { filterDialogState.value = false }
                )

            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                CustomSideIconButton(
                    text = "TODO",
                    onClick = {
                        documentSelectViewModel.filterDocuments(Arrays.asList(0, 1, 2, 3, 4))
                        selectedFilter.value = -1
                        filterDialogState.value = false
                    },
                    buttonSize = 250,
                    buttonColor = when (selectedFilter.value) {
                        -1 -> buttonOkColor
                        else -> Color.LightGray
                    },
                    textColor = when (selectedFilter.value) {
                        -1 -> Color.Black
                        else -> Color.Gray
                    },
                )
                CustomSideIconButton(
                    text = "IMAGEN",
                    onClick = {
                        documentSelectViewModel.filterDocuments(Arrays.asList(0))
                        selectedFilter.value = 0
                        filterDialogState.value = false
                    },
                    buttonSize = 250,
                    buttonColor = when (selectedFilter.value) {
                        0 -> buttonOkColor
                        else -> Color.LightGray
                    },
                    textColor = when (selectedFilter.value) {
                        0 -> Color.Black
                        else -> Color.Gray
                    },
                )
                CustomSideIconButton(
                    text = "VIDEO",
                    onClick = {
                        documentSelectViewModel.filterDocuments(Arrays.asList(1))
                        selectedFilter.value = 1
                        filterDialogState.value = false
                    },
                    buttonSize = 250,
                    buttonColor = when (selectedFilter.value) {
                        1 -> buttonOkColor
                        else -> Color.LightGray
                    },
                    textColor = when (selectedFilter.value) {
                        1 -> Color.Black
                        else -> Color.Gray
                    },
                )
                CustomSideIconButton(
                    text = "AUDIO",
                    onClick = {
                        documentSelectViewModel.filterDocuments(Arrays.asList(2))
                        selectedFilter.value = 2
                        filterDialogState.value = false
                    },
                    buttonSize = 250,
                    buttonColor = when (selectedFilter.value) {
                        2 -> buttonOkColor
                        else -> Color.LightGray
                    },
                    textColor = when (selectedFilter.value) {
                        2 -> Color.Black
                        else -> Color.Gray
                    },
                )
                CustomSideIconButton(
                    text = "PDF",
                    onClick = {
                        documentSelectViewModel.filterDocuments(Arrays.asList(4))
                        selectedFilter.value = 4
                        filterDialogState.value = false
                    },
                    buttonSize = 250,
                    buttonColor = when (selectedFilter.value) {
                        4 -> buttonOkColor
                        else -> Color.LightGray
                    },
                    textColor = when (selectedFilter.value) {
                        4 -> Color.Black
                        else -> Color.Gray
                    },
                )

            }

        }

    }

}

