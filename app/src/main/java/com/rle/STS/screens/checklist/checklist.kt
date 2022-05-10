package com.rle.STS.screens.checklist

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.rle.STS.R
import com.rle.STS.viewScreens.data.*
import com.rle.STS.ui.theme.topBarColor
import com.rle.STS.screens.CkViewRepository
import com.rle.STS.widgets.BottomButtons
import com.rle.STS.widgets.CustomButton
import kotlinx.coroutines.launch

@Composable
fun ChecklistScreen(
    navController: NavController,
    //TODO - Add ViewModel
) {

    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()
    val openConfirmDialog = remember { mutableStateOf(false) }

    val checkList = createMockCheclist()
    val viewModel = ChecklistViewModel()
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
                    title = "Título de checklist de ejemplo",
                    description = "Lorem ipsum es el texto que se usa habitualmente en diseño gráfico o de moda en demostraciones de tipografías o de borradores de diseño.",
                    viewModel
                )

                "imagen" -> CkViewRepository.IM1(
                    "Capture.jpeg",
                    viewModel
                )//ImageScreen(file = "Capture.PNG", type = 0, viewModel)

                "video" -> CkViewRepository.VD1(file = "The RealWear HMT-1.mp4", viewModel)

                "audio" -> CkViewRepository.AU2(file = "Grabacion.m4a", viewModel)

                "QR" -> CkViewRepository.QR1(viewModel = viewModel, nextType = 0)

                "Record_Audio" -> CkViewRepository.AU3(viewModel, nextType = 0)

                "Dictate" -> CkViewRepository.AU1(viewModel, nextType = 0)

                "Take_Picture" -> CkViewRepository.CM1(viewModel, nextType = 0)

                "Take_Video" -> CkViewRepository.CM2(viewModel, nextType = 0)

                "Number" -> CkViewRepository.NumberScreen1(
                    check = { },
                    viewModel,
                    nextType = 0
                ) //Hueco que indique si el numero es correcto o incorrecto y comprobar al pulsar siguiente

                "OK/KO" -> CkViewRepository.OP1(viewModel, nextType = 0)

                "MultiOption" -> CkViewRepository.MultiOption2(viewModel = viewModel, nextType = 0)

                //TODO: Colocar aqui las vistas de checklist

            }



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
    viewModel: ChecklistViewModel
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
        //"video",
        //"audio",
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

//@Preview(showBackground = true, widthDp = 851, heightDp = 480)
//@Composable
//private fun DefaultPreview() {
//    CheckListaApplicationTheme {
//        CheckListStepScreen()
//    }
//}