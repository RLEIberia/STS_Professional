package com.rle.STS.screens.checklistSelect

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rle.STS.ActivityViewModel
import com.rle.STS.R
import com.rle.STS.navigation.STSScreens
import com.rle.STS.screens.Drawer
import com.rle.STS.ui.theme.specialButtonColor
import com.rle.STS.widgets.BottomBar
import com.rle.STS.widgets.ListRow
import com.rle.STS.widgets.SimpleTopBar
import kotlinx.coroutines.launch

@Composable
fun ChecklistSelectScreen(
    navController: NavController,
    checklistSelectViewModel: ChecklistSelectViewModel = hiltViewModel(),
    activityViewModel: ActivityViewModel = hiltViewModel()
    //TODO viewModel
) {
    val userDbData = activityViewModel.userDbData.collectAsState().value
    val selectedProject = activityViewModel.selectedProject.collectAsState().value
    val checklistList = checklistSelectViewModel.checklistList.collectAsState().value

    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    val context = LocalContext.current
    val buttonWidth = 250

    Log.d("PROJECT", selectedProject.toString())

    if (selectedProject.id != -1) {
        checklistSelectViewModel.getChecklistsByProject(selectedProject.id)
        Log.d("PROJECT", selectedProject.toString())
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            SimpleTopBar(scope, scaffoldState)
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
                centerText = context.getString(R.string.navigate_back),
                centerSize = buttonWidth,
                centerColor = specialButtonColor,
                centerOnClick = { navController.popBackStack() },
                rightActive = (indexCounter.value + 4 < checklistList.size),
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
        if (checklistList.isNotEmpty()) {

            Surface(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
            ) {
                LazyColumn(
                    state = listState
                ) {
                    itemsIndexed(items = checklistList) { index, item ->
                        ListRow(
                            buttonLetter = "C",
                            buttonColor = Color(0xffff8000),
                            rowColor = Color.White,
                            textColor = Color.Black,
                            number = index,
                            title = item.name,
                            dateTimeStamp = item.updated_at,
                            onClick = {
                                activityViewModel.getChecklistById(checklistList[index].id)
                                navController.navigate(STSScreens.ChecklistScreen.name)
                            }
                        )
                    }
                }

            }
        }

    }


}