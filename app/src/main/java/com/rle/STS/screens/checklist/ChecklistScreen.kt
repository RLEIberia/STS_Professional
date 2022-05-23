package com.rle.STS.screens.checklist

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
import com.rle.STS.ui.theme.topBarColor
import com.rle.STS.screens.ViewRepository
import com.rle.STS.ui.theme.specialButtonColor
import com.rle.STS.screens.viewScreens.ViewScreens
import com.rle.STS.widgets.BottomBarChecklist
import com.rle.STS.widgets.CustomButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ChecklistScreen(
    navController: NavController,
    checklistViewModel: ChecklistViewModel = hiltViewModel()
) {

    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()
    val openConfirmDialog = remember { mutableStateOf(false) }

    val context = LocalContext.current
    val currentStep = checklistViewModel.currentStep.collectAsState()
    val currentView = checklistViewModel.currentView.collectAsState()

    val checklist = checklistViewModel.checklist.collectAsState().value


    checklistViewModel.extractChecklist(fileName = "exampleChecklist.json", context = context)

//    LaunchedEffect(true) {
//        Log.d("LAUNCH", "LaunchedEffect finished")
//        //Recogemos la información del archivo JSON
//        checklistViewModel.extractChecklist(fileName = "exampleChecklist.json", context = context)
//        //TODO cargar datos si existía
//        //TODO Que se reciba el json como parámetro
//    }

    Scaffold(
        topBar = {
            checklistTopBar(
                scope = scope,
                scaffoldState = scaffoldState,
                checklistViewModel = checklistViewModel
            )
        },
        bottomBar = {
            BottomBarChecklist(
                modifier = Modifier,
                checklistViewModel = checklistViewModel,
                backOnClick = { /*TODO*/ },
                centerActive = true,
                centerText = "TEST",
                centerOnClick = { /*TODO*/ },
                rightActive = true,
                centerColor = specialButtonColor
            )
        },
        drawerContent = {
            //TODO
        }
    ) {
        // Cargar JSON y seleccionar vista actual, Crear metodo que lea JSON y devuelva siguiente vista

        //ESPERAMOS A QUE EL VIEWMODEL HAYA CARGADO LOS DATOS
        if (checklist.checklistData != null) {

            Log.d("POS", currentView.value.toString())


            Surface(
                modifier = Modifier
                    .padding(it)
            ) {

                when (checklist.checklistData.steps[currentStep.value].views[currentView.value].viewType) {


                    //Image
                    ViewScreens.IM1.name -> ViewRepository.IM1(
                        viewModel = checklistViewModel,
                    )
                    ViewScreens.IM2.name -> ViewRepository.IM2(
                        viewModel = checklistViewModel,
                    )
                    ViewScreens.IM3.name -> ViewRepository.IM3(
                        viewModel = checklistViewModel
                    )

                    //Video
                    ViewScreens.VD1.name -> ViewRepository.VD1(
                        checklistViewModel = checklistViewModel
                    )

                    //Text TODO -> ADD TO REPOSITORY
                    ViewScreens.TX1.name -> {
                        ViewRepository.TX1(checklistViewModel = checklistViewModel)
                    }
                    ViewScreens.TX2.name -> {
                        ViewRepository.TX2(checklistViewModel = checklistViewModel)
                    }
                    ViewScreens.TX3.name -> {
                        ViewRepository.TX3(checklistViewModel = checklistViewModel)
                    }

                    //Camera
                    ViewScreens.CM1.name -> ViewRepository.CM1(
                        checklistViewModel = checklistViewModel
                    )
                    ViewScreens.CM2.name -> ViewRepository.CM2(
                        checklistViewModel = checklistViewModel,
                    )

                    //Number
                    ViewScreens.NM1.name -> ViewRepository.NM1(
                        viewModel = checklistViewModel,
                        nextType = 0,
                        check = { })
                    ViewScreens.NM2.name -> {}//ViewRepository.NM2(viewModel = checklistViewModel)

                    //Option
                    ViewScreens.OP1.name -> ViewRepository.OP1(
                        viewModel = checklistViewModel
                    )
                    ViewScreens.OP2.name -> {} //ViewRepository.OP2(viewModel = checklistViewModel)

                    //QR
                    ViewScreens.QR1.name -> ViewRepository.QR1(
                        viewModel = checklistViewModel,
                        nextType = 0
                    )
                    ViewScreens.QR2.name -> ViewRepository.QR2(
                        viewModel = checklistViewModel,
                        nextType = 0
                    )
                    ViewScreens.QR3.name -> ViewRepository.QR3(
                        viewModel = checklistViewModel,
                        nextType = 0
                    )
                    ViewScreens.QR4.name -> {} //ViewRepository.QR4(viewModel = checklistViewModel, nextType =

                    //Audio
                    ViewScreens.AU1.name -> ViewRepository.AU1(
                        viewModel = checklistViewModel,
                        nextType = 0
                    )
                    ViewScreens.AU2.name -> ViewRepository.AU2(
                        viewModel = checklistViewModel,
                        file = ""
                    )
                    ViewScreens.AU3.name -> ViewRepository.AU3(
                        viewModel = checklistViewModel,
                        nextType = 0
                    )

                    else -> {}

                }
            }
        }
    }
}

//@Composable
//private fun oldViewCall(
//    checkList: ArrayList<String>,
//    checkListPosition: State<Int>,
//    viewModel: ChecklistViewModel,
//    openConfirmDialog: MutableState<Boolean>
//) {
//    Column {
//
//        Spacer(modifier = Modifier.weight(1f))
//
//        // DATA SCREENS
//        when (checkList[checkListPosition.value]) {
//
//            "texto" -> TextScreen(
//                title = "Título de checklist de ejemplo",
//                description = "Lorem ipsum es el texto que se usa habitualmente en diseño gráfico o de moda en demostraciones de tipografías o de borradores de diseño.",
//                viewModel
//            )
//
//            "imagen" -> ViewRepository.IM1(
//                "Capture.jpeg",
//                viewModel
//            )//ImageScreen(file = "Capture.PNG", type = 0, viewModel)
//
//            "video" -> ViewRepository.VD1(file = "The RealWear HMT-1.mp4", viewModel)
//
//            "audio" -> ViewRepository.AU2(file = "Grabacion.m4a", viewModel)
//
//            "QR" -> ViewRepository.QR1(viewModel = viewModel, nextType = 0)
//
//            "Record_Audio" -> ViewRepository.AU3(viewModel, nextType = 0)
//
//            "Dictate" -> ViewRepository.AU1(viewModel, nextType = 0)
//
//            "Take_Picture" -> ViewRepository.CM1(viewModel, nextType = 0)
//
//            "Take_Video" -> ViewRepository.CM2(viewModel, nextType = 0)
//
//            "Number" -> ViewRepository.NM1(
//                check = { },
//                viewModel,
//                nextType = 0
//            ) //Hueco que indique si el numero es correcto o incorrecto y comprobar al pulsar siguiente
//
//            "OK/KO" -> ViewRepository.OP1(viewModel, nextType = 0)
//
//            "MultiOption" -> ViewRepository.MultiOption2(viewModel = viewModel, nextType = 0)
//
//            //TODO: Colocar aqui las vistas de checklist
//
//        }
//
//
//        /* TODO
//                CheckNumberScreen() Probar a utilizar la pantalla NumberScreen
//                MultiOptionQR()     Probar a utilizar la pantalla QRScreen
//             */
//
//        if (openConfirmDialog.value) {
//            ShowConfirmDialog(
//                openConfirmDialog = openConfirmDialog,
//                checkList = checkList,
//                viewModel = viewModel
//            )
//        }
//
//    }
//}

@Composable
private fun checklistTopBar(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    checklistViewModel: ChecklistViewModel = hiltViewModel()
) {
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
}


//@OptIn(ExperimentalComposeUiApi::class)
//@Composable
//fun ShowConfirmDialog(
//    openConfirmDialog: MutableState<Boolean>,
//    checkList: ArrayList<String>,
//    viewModel: ChecklistViewModel
//) {
//
//    AlertDialog(
//        properties = DialogProperties(
//            usePlatformDefaultWidth = false
//        ),
//        modifier = Modifier
//            .width(450.dp),
//        onDismissRequest = {
//            openConfirmDialog.value = false
//        },
//        text = {
//            Column(horizontalAlignment = Alignment.CenterHorizontally) {
//
//                Text(text = stringResource(id = R.string.confirm_continue), fontSize = 30.sp)
//
//            }
//        },
//        buttons = {
//            Row(
//                modifier = Modifier.padding(all = 8.dp),
//                horizontalArrangement = Arrangement.Center
//            ) {
//                BottomButtons(
//                    leftFunction = { openConfirmDialog.value = false },
//                    leftText = stringResource(id = R.string.cancel),
//                    rightFunction = {
//                        val position = viewModel.checkListPosition
//                        if (position.value < checkList.size - 1) {
//                            viewModel.setPosition(position.value + 1)
//                        } else {
//                            //finalizar checklist
//                        }
//                    },
//                    rightText = stringResource(id = R.string.confirm)
//                )
//            }
//        }
//    )
//}


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