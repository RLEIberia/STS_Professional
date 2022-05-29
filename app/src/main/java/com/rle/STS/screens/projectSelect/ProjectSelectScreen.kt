package com.rle.STS.screens.projectSelect

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rle.STS.ActivityViewModel
import com.rle.STS.navigation.STSScreens
import com.rle.STS.screens.Drawer
import com.rle.STS.screens.ListScreen
import com.rle.STS.ui.theme.specialButtonColor
import com.rle.STS.widgets.BottomBar
import com.rle.STS.widgets.CustomSideIconButton
import com.rle.STS.widgets.ListRow
import com.rle.STS.widgets.SimpleTopBar
import kotlinx.coroutines.launch
import javax.inject.Scope

@Composable
fun ProjectSelectScreen(
    navController: NavController,
    projectsViewModel: ProjectsViewModel = hiltViewModel(),
    activityViewModel: ActivityViewModel = hiltViewModel()
) {
    val userDbData = activityViewModel.userDbData.collectAsState().value
    val projectsList = projectsViewModel.userProjects.collectAsState().value

    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    val context = LocalContext.current
    val buttonWidth = 250

    if (userDbData.id != -1) {
        projectsViewModel.getUserProjects(userDbData)
    }

    if (projectsList.isNotEmpty()) {

        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                SimpleTopBar(
                    scope,
                    scaffoldState,
                    text = "PROYECTOS"
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
                    centerText = "IR ATRÁS",
                    centerSize = buttonWidth,
                    centerColor = specialButtonColor,
                    centerOnClick = { navController.popBackStack() },
                    rightActive = (indexCounter.value + 4 < projectsList.size),
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
                    itemsIndexed(items = projectsList) { index, item ->
                        ListRow(
                            buttonLetter = "P",
                            buttonColor = Color(0xffff8000),
                            rowColor = Color.White,
                            textColor = Color.Black,
                            number = index,
                            title = item.name,
                            dateTimeStamp = item.updated_at,
                            onClick = {
                                Log.d("SELECTED", projectsList[index].toString())
                                activityViewModel.getProjectById(projectsList[index].id)
                                navController.navigate(STSScreens.ChecklistSelectScreen.name)
                            }
                        )
                    }
                }
            }
        }
    }
}