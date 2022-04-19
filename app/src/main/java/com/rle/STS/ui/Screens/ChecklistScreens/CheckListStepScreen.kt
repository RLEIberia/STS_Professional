package com.rle.STS.ui.Screens.ChecklistScreens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.rle.STS.R
import com.rle.STS.ui.Screens.ChecklistScreens.Attached.*
import com.rle.STS.ui.Screens.ChecklistScreens.Data.*
import com.rle.STS.ui.Screens.ChecklistScreens.Repository.Repository
import com.rle.STS.ui.Screens.ChecklistScreens.Result.MultiOptionScreen
import com.rle.STS.ui.Screens.ChecklistScreens.Result.OKKOScreen
import com.rle.STS.ui.theme.CheckListaApplicationTheme
import com.rle.STS.ui.theme.topBarColor
import com.rle.STS.ui.widgets.BottomButtons
import com.rle.STS.ui.widgets.CustomButton
import kotlinx.coroutines.launch

@Composable
fun CheckListStepScreen() {

    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()
    val openConfirmDialog = remember { mutableStateOf(false) }

    val checkList = createMockCheclist()
    val viewModel = CheckListStepViewModel()
    val checkListPosition = viewModel.checkListPosition.collectAsState()
    viewModel.setSize(checkList.size)

    Scaffold(
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
                        CustomButton(text = stringResource(R.string.audio), onClick = {
                            scope.launch {
                                scaffoldState.drawerState.open()
                            }
                        })
                        Spacer(modifier = Modifier.width(10.dp))
                    }
                    Spacer(modifier = Modifier.weight(1f))
                }

            }
        },

        ) { // Cargar JSON y seleccionar vista actual, Crear metodo que lea JSON y devuelva siguiente vista

        Column {

            Spacer(modifier = Modifier.weight(1f))

            // DATA SCREENS
            when (checkList[checkListPosition.value]) {

                "texto" -> TextScreen(
                    title = "Elemento 1 checklist Title",
                    description = "Elemento 1 checklist Description",
                    viewModel
                )

                "imagen" -> Repository.Im1("Capture.PNG", viewModel)//ImageScreen(file = "Capture.PNG", type = 0, viewModel)

                "video" -> VideoScreen(file = "test.mp4", viewModel)

                "audio" -> AudioScreen(fileName = "Grabacion.m4a", viewModel)

                "QR" -> QRScreen(type = 3, check = { true }, viewModel)

                "Record_Audio" -> RecordAudioScreen(viewModel)

                "Dictate" -> DictateScreen(viewModel)

                "Take_Picture" -> TakePictureScreen(viewModel)

                "Take_Video" -> TakeVideoScreen(viewModel)

                "Number" -> NumberScreen(check = { }, viewModel) //Hueco que indique si el numero es correcto o incorrecto y comprobar al pulsar siguiente

                "OK/KO" -> OKKOScreen(viewModel)

                "MultiOption" -> MultiOptionScreen(
                    option1 = "option1",
                    option2 = "option2",
                    option3 = "option3",
                    option4 = "option4",
                    viewModel
                )

                //TODO: Colocar aqui las vistas de checklist

            }

            //TextScreen(title = "Elemento 1 checklist Title", description = "Elemento 1 checklist Description", viewModel)
            //ImageScreen(file = "169.png", type = 1, viewModel)
            //ImageScreen(file = "Capture.PNG", type = 1, viewModel)
            //VideoScreen(file = "test.mp4", viewModel)
            //AudioScreen(fileName = "Grabacion.m4a", viewModel)


            // MULTI-USE SCREENS
            //QRScreen(type = 3, check = { true }, viewModel)

            // ATTACHED SCREENS

            //RecordAudioScreen()
            //DictateScreen()
            //TakePictureScreen()
            //TakeVideoScreen()
            //NumberScreen( check = {  } )

            // RESULT SCREENS

            //OKKOScreen()
            //MultiOptionScreen(option1 = "option1", option2 = "option2") //option3 = "option3", option4 = "option4")

            /* TODO
                CheckNumberScreen() Probar a utilizar la pantalla NumberScreen
                MultiOptionQR()     Probar a utilizar la pantalla QRScreen
             */

            if (openConfirmDialog.value) {
                ShowConfirmDialog(
                    openConfirmDialog = openConfirmDialog,
                    checkList = checkList,
                    viewModel = viewModel
                )
            }

        }

    }

}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ShowConfirmDialog(
    openConfirmDialog: MutableState<Boolean>,
    checkList: ArrayList<String>,
    viewModel: CheckListStepViewModel
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

                Text(text = stringResource(id = R.string.confirm_continue), fontSize = 30.sp)

            }
        },
        buttons = {
            Row(
                modifier = Modifier.padding(all = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                BottomButtons(
                    leftFunction = { openConfirmDialog.value = false },
                    leftText = stringResource(id = R.string.cancel),
                    rightFunction = {
                        val position = viewModel.checkListPosition
                        if (position.value < checkList.size - 1) {
                            viewModel.setPosition(position.value + 1)
                        } else {
                            //finalizar checklist
                        }
                    },
                    rightText = stringResource(id = R.string.confirm)
                )
            }
        }
    )
}


fun createMockCheclist(): ArrayList<String> {
    return arrayListOf<String>(
        "texto",
        "imagen",
        "video",
        "audio",
        "QR",
        "Record_Audio",
        "Dictate",
        "Take_Picture",
        "Take_Video",
        "Number",
        "OK/KO",
        "MultiOption"
    )
}

@Preview(showBackground = true, widthDp = 851, heightDp = 480)
@Composable
private fun DefaultPreview() {
    CheckListaApplicationTheme {
        CheckListStepScreen()
    }
}