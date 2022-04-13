package com.rle.STS.Screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.rle.STS.R
import com.rle.STS.ui.Screens.Drawer
import com.rle.STS.ui.theme.CheckListaApplicationTheme
import com.rle.STS.ui.theme.cardsColor
import com.rle.STS.ui.theme.topBarColor
import com.rle.STS.ui.widgets.BottomButtons
import com.rle.STS.ui.widgets.CustomButton
import kotlinx.coroutines.launch


@Composable
fun ProjectsChecklists(
    onListClick: () -> Unit,
    itemsList: ArrayList<String>,
    letter: String,
    title: String
) {

    var page by remember { mutableStateOf(1) }
    val openConfirmDialog = remember { mutableStateOf(false) }
    var limitPage: Boolean
    //rememberSaveable for composition change
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()
    val clickedList = remember { mutableStateOf(letter + "0") }
    val context = LocalContext.current

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
                        CardView(
                            text = number,
                            clickable = true,
                            onListClick = {
                                if (letter == "P") {
                                    onListClick()
                                } else {
                                    openConfirmDialog.value = true
                                    clickedList.value = number
                                }
                            }
                        )
                    }
                    Box(modifier = Modifier.weight(8.9f)) {
                        CardView(
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
                middleFunction = {  }, // ir atras
                middleInteractable = false,
                leftVisible = page != 1,
                rightVisible = !limitPage
            )

            Spacer(modifier = Modifier.height(10.dp))

        }

    }

    if (openConfirmDialog.value){
        ShowConfirmDialog(openConfirmDialog = openConfirmDialog, onListClick = onListClick, clickedList = clickedList)
    }

}


//Cards para mostrar los proyectos o checklists
@Composable
fun CardView(text: String, clickable: Boolean, onListClick: () -> Unit = {}) {

    Card(
        shape = RoundedCornerShape(8.dp),
        backgroundColor = cardsColor,
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
                Row() {
                    Column() {
                        Spacer(modifier = Modifier.weight(1f))
                        Text(text = text, fontSize = 25.sp)

                        Spacer(modifier = Modifier.weight(1f))

                        Text(text = text, fontSize = 15.sp)
                        Spacer(modifier = Modifier.weight(1f))
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Column() {
                        Spacer(modifier = Modifier.weight(1f))
                        Text(text = "Actualizado:", fontSize = 15.sp)
                        Spacer(modifier = Modifier.weight(1f))
                        Text(text = "23-03-2022", fontSize = 15.sp)
                        Spacer(modifier = Modifier.weight(1f))
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                }
            }
        }
    }

}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ShowConfirmDialog(
    openConfirmDialog: MutableState<Boolean>,
    clickedList: MutableState<String>,
    onListClick: () -> Unit,
) {

    AlertDialog(
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),
        modifier = Modifier
            .width(450.dp),
        onDismissRequest = {
            openConfirmDialog.value = false
        },
        text = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                Text(text = clickedList.value, fontSize = 30.sp)

            }
        },
        buttons = {
            Row(
                modifier = Modifier.padding(all = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                BottomButtons(
                    leftFunction = { openConfirmDialog.value = false },
                    leftText = stringResource(id = R.string.cancel),//ROJO
                    rightFunction = {
                        onListClick()
                    },
                    rightText = stringResource(id = R.string.confirm)//VERDE
                )
            }
        }
    )
}

@Preview(showBackground = true, widthDp = 851, heightDp = 480)
@Composable
fun DefaultPreview() {
    CheckListaApplicationTheme {
        val itemsList = ArrayList<String>()
        itemsList.add("Proyecto uno")
        itemsList.add("Proyecto dos")
        itemsList.add("Proyecto tres")
        itemsList.add("Proyecto cuatro")
        itemsList.add("Proyecto cinco")
        itemsList.add("Proyecto seis")
        itemsList.add("Proyecto siete")
        itemsList.add("Proyecto ocho")
        itemsList.add("Proyecto nueve")
        itemsList.add("Proyecto diez")
        itemsList.add("Proyecto once")
        itemsList.add("Proyecto doce")
        itemsList.add("Proyecto trece")
        itemsList.add("Proyecto catorce")
        itemsList.add("Proyecto quince")
        itemsList.add("Proyecto dieciseis")
        itemsList.add("Proyecto diecisiete")

        ProjectsChecklists ({ }, itemsList = itemsList, title = "", letter = "P")
    }
}