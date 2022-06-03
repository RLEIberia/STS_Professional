package com.rle.STS.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.rle.STS.ActivityViewModel
import com.rle.STS.navigation.STSScreens
import com.rle.STS.screens.checklist.ChecklistViewModel
import com.rle.STS.screens.state.StateViewModel
import com.rle.STS.ui.theme.specialButtonColor
import com.rle.STS.widgets.BottomBar
import com.rle.STS.widgets.ListRow
import com.rle.STS.widgets.SimpleTopBar
import kotlinx.coroutines.launch

@Composable
fun StateScreen(
    navController: NavController,
    activityViewModel: ActivityViewModel,
    stateViewModel: StateViewModel,
    checklistViewModel: ChecklistViewModel
) {

    val executionList = stateViewModel.executionList.collectAsState()

    val userDbData = activityViewModel.userDbData.collectAsState().value
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    val context = LocalContext.current
    val buttonWidth = 250


    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            SimpleTopBar(
                scope,
                scaffoldState,
                text = "EJECUCIONES"
            )
        },
        drawerContent = {
            Drawer(scaffoldState = scaffoldState, scope = scope)
        },
        bottomBar = {
            val indexCounter = remember {
                mutableStateOf(listState.firstVisibleItemIndex)
            }
            BottomBar(
                modifierBottomBar = Modifier,
                leftActive = (indexCounter.value - 4 >= 0),
                leftText = "SUBIR",
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
                centerText = "LIMPIAR",
                centerSize = buttonWidth,
                centerColor = specialButtonColor,
                centerOnClick = { stateViewModel.clearExecutions() },
                rightActive = (indexCounter.value + 4 < executionList.value.size),
                rightText = "BAJAR",
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

        Surface(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            LazyColumn(
                state = listState
            ) {
                itemsIndexed(items = executionList.value) { index, item ->
                    ListRow(
                        buttonLetter = "E",
                        buttonColor = Color(0xffff8000),
                        rowColor = Color.White,
                        textColor = Color.Black,
                        number = index,
                        title = item.id_ck_version.toString(),
                        dateTimeStamp = item.updated_at,
                        onClick = {
                            Log.d("SELECTED", item.toString())
                            activityViewModel.setExecution(item)
                            navController.navigate(STSScreens.ChecklistScreen.name)
                        }
                    )
                }
            }
        }
    }


}
