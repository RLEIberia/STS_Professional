package com.rle.STS.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rle.STS.R
import com.rle.STS.navigation.STSScreens
import com.rle.STS.screens.projectSelect.ProjectsViewModel
import com.rle.STS.screens.utils.ListCardView
import com.rle.STS.screens.utils.ListConfirmDialog
import com.rle.STS.ui.theme.topBarColor
import com.rle.STS.widgets.BottomButtons
import com.rle.STS.widgets.CustomButton
import kotlinx.coroutines.launch


@Composable
fun ListScreen(
    itemsList: ArrayList<String>,
    letter: String,
    title: String,
    navController: NavController,
    //TODO cambiar lo de viewModel
    ProjectsViewModel: ProjectsViewModel = hiltViewModel()
) {

    var page by remember { mutableStateOf(1) }
    val openConfirmDialog = remember { mutableStateOf(false) }
    var limitPage: Boolean
    //rememberSaveable for composition change
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()
    val clickedList = remember { mutableStateOf(letter + "0") }
    val context = LocalContext.current

    //TODO DATOS DESDE DB
    val projectsFromDb = ProjectsViewModel.projectsList.collectAsState().value

    Log.d("Projects", "$projectsFromDb")

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                backgroundColor = topBarColor,
                modifier = Modifier.height(60.dp)
            ) {
                Column() {
                    Spacer(modifier = Modifier.weight(1f))
                    Row() {
                        Spacer(modifier = Modifier.width(10.dp))
                        CustomButton(text = stringResource(R.string.menu), onClick = {
                            scope.launch {
                                scaffoldState.drawerState.open()
                            }
                        })
                        Spacer(modifier = Modifier.weight(1f))
                    }
                    Spacer(modifier = Modifier.weight(1f))
                }

            }
        },
        drawerContent = {
            Drawer(scaffoldState = scaffoldState, scope = scope)
        },
    ) { contentPadding ->

        // Screen content
        Column(modifier = Modifier.padding(contentPadding)) {

            val localList: MutableList<String>

            if (itemsList.size >= page * 4 + 1) {
                limitPage = false
                localList = itemsList.subList((page - 1) * 4, page * 4)
            } else {
                limitPage = true
                localList = itemsList.subList((page - 1) * 4, itemsList.size)
            }

            Spacer(modifier = Modifier.height(3.dp))

            for (item in localList) {
                val index = localList.indexOf(item)

                Row(
                    Modifier.fillMaxWidth()
                ) {
                    val number = letter + ((page - 1) * 4 + index + 1)
                    Spacer(modifier = Modifier.width(10.dp))

                    Box(modifier = Modifier.weight(1.1f)) {
                        ListCardView(
                            text = number,
                            clickable = true,
                            onListClick = {
                                if (letter == "P") {
                                    navController.navigate(STSScreens.ChecklistSelectScreen.name)
                                } else {
                                    openConfirmDialog.value = true
                                    clickedList.value = number
                                }
                            }
                        )
                    }
                    Box(modifier = Modifier.weight(8.9f)) {
                        ListCardView(
                            text = item,
                            clickable = false,
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))

                }
            }

            Spacer(modifier = Modifier.weight(1f))

            BottomButtons(
                leftFunction = {
                    if (page > 1) {
                        page--
                    }
                },
                leftText = stringResource(id = R.string.up),
                rightFunction = {
                    if (!limitPage) {
                        page++
                    }
                },
                rightText = stringResource(id = R.string.down),
                middleText = stringResource(id = R.string.go_back),
                middleFunction = { }, // ir atras
                middleInteractable = false,
                leftVisible = page != 1,
                rightVisible = !limitPage
            )

            Spacer(modifier = Modifier.height(10.dp))

        }

    }

    if (openConfirmDialog.value) {
        ListConfirmDialog(
            openConfirmDialog = openConfirmDialog,
            onListClick = {navController.navigate(STSScreens.Checklist.name)},
            clickedList = clickedList
        )
    }

}


//@Preview(showBackground = true, widthDp = 851, heightDp = 480)
//@Composable
//fun DefaultPreview() {
//    CheckListaApplicationTheme {
//        val itemsList = ArrayList<String>()
//        itemsList.add("Proyecto uno")
//        itemsList.add("Proyecto dos")
//        itemsList.add("Proyecto tres")
//        itemsList.add("Proyecto cuatro")
//        itemsList.add("Proyecto cinco")
//        itemsList.add("Proyecto seis")
//        itemsList.add("Proyecto siete")
//        itemsList.add("Proyecto ocho")
//        itemsList.add("Proyecto nueve")
//        itemsList.add("Proyecto diez")
//        itemsList.add("Proyecto once")
//        itemsList.add("Proyecto doce")
//        itemsList.add("Proyecto trece")
//        itemsList.add("Proyecto catorce")
//        itemsList.add("Proyecto quince")
//        itemsList.add("Proyecto dieciseis")
//        itemsList.add("Proyecto diecisiete")
//
//        ListScreen({ }, itemsList = itemsList, title = "", letter = "P")
//    }
//}