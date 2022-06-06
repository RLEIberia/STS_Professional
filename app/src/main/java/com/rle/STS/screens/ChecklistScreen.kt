package com.rle.STS.screens.checklist

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.rle.STS.viewModel.ActivityViewModel
import com.rle.STS.R
import com.rle.STS.navigation.STSScreens
import com.rle.STS.screens.ChecklistDrawer
import com.rle.STS.screens.ViewRepository
import com.rle.STS.ui.theme.specialButtonColor
import com.rle.STS.screens.viewScreens.ViewScreens
import com.rle.STS.ui.theme.buttonKoColor
import com.rle.STS.ui.theme.buttonOkColor
import com.rle.STS.viewModel.ChecklistViewModel
import com.rle.STS.widgets.BottomBarChecklist
import com.rle.STS.widgets.CustomDialog
import com.rle.STS.widgets.SimpleTopBar

@Composable
fun ChecklistScreen(
    navController: NavController,
    checklistViewModel: ChecklistViewModel,
    activityViewModel: ActivityViewModel
) {

    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val currentStep = checklistViewModel.currentStep.collectAsState()
    val currentView = checklistViewModel.currentView.collectAsState()
    val endDialog = checklistViewModel.endDialog.collectAsState()
    val checklist = checklistViewModel.checklist.collectAsState().value


    LaunchedEffect(
        key1 = true,
        block = {
            if (activityViewModel.selectedExecution.value.id == null) {
                checklistViewModel.startChecklistExecution(
                    selectedExecutionId = null,
                    context = context,
                    userId = activityViewModel.userSimple.value.userCode,
                    idCkVersion = activityViewModel.selectedChecklist.value.id
                )
            } else {
                checklistViewModel.startChecklistExecution(
                    selectedExecutionId = activityViewModel.selectedExecution.value.id!!,
                    context = context,
                    userId = activityViewModel.userSimple.value.userCode,
                    idCkVersion = activityViewModel.selectedExecution.value.id_ck_version
                )
            }
        })

    if (endDialog.value) {
        EndChecklistDialog(
            navController = navController,
            checklistViewModel = checklistViewModel
        )
    }

    //Wait for the viewModel data to charge
    if (checklist.checklistData != null
        && currentStep.value != -1
        && currentView.value != -1
    ) {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                SimpleTopBar(
                    scope = scope,
                    scaffoldState = scaffoldState,
                    text = checklist.checklistData!!.steps[currentStep.value].stepName,
                    rightExist = true,
                    rightText = "AUDIO",
                    rightOnClick = { checklistViewModel.buttonSpeak() }
                )

            },
            bottomBar = {
                BottomBarChecklist(
                    modifier = Modifier,
                    checklistViewModel = checklistViewModel,
                    rightActive = true,
                    centerColor = specialButtonColor
                )
            },
            drawerContent = {
                ChecklistDrawer(
                    scope = scope,
                    scaffoldState = scaffoldState,
                    navController = navController,
                    checklistViewModel = checklistViewModel
                )
            }
        ) {

            Surface(
                modifier = Modifier
                    .padding(it)
            ) {

                when (checklist.checklistData.steps[currentStep.value]
                    .views[currentView.value].viewType) {


                    ViewScreens.IM1.name -> ViewRepository.IM1(viewModel = checklistViewModel)
                    ViewScreens.IM2.name -> ViewRepository.IM2(viewModel = checklistViewModel)
                    ViewScreens.IM3.name -> ViewRepository.IM3(viewModel = checklistViewModel)
                    ViewScreens.VD1.name -> ViewRepository.VD1(checklistViewModel = checklistViewModel)

                    ViewScreens.TX1.name -> { ViewRepository.TX1(checklistViewModel = checklistViewModel) }
                    ViewScreens.TX2.name -> { ViewRepository.TX2(checklistViewModel = checklistViewModel) }
                    ViewScreens.TX3.name -> { ViewRepository.TX3(checklistViewModel = checklistViewModel) }

                    ViewScreens.CM1.name -> ViewRepository.CM1(checklistViewModel = checklistViewModel)
                    ViewScreens.CM2.name -> ViewRepository.CM2(checklistViewModel = checklistViewModel)

                    ViewScreens.NM1.name -> ViewRepository.NM1(checklistViewModel = checklistViewModel)
                    ViewScreens.NM2.name -> {}//ViewRepository.NM2(viewModel = checklistViewModel)

                    ViewScreens.OP1.name -> ViewRepository.OP1(checklistViewModel = checklistViewModel)
                    ViewScreens.OP2.name -> ViewRepository.OP2(checklistViewModel = checklistViewModel)

                    ViewScreens.QR1.name -> ViewRepository.QR1(checklistViewModel = checklistViewModel,)
//                    ViewScreens.QR2.name -> ViewRepository.QR2(
//                        viewModel = checklistViewModel,
//                        nextType = 0
//                    )
//                    ViewScreens.QR3.name -> ViewRepository.QR3(
//                        viewModel = checklistViewModel,
//                        nextType = 0
//                    )
                    ViewScreens.QR4.name -> {} //ViewRepository.QR4(viewModel = checklistViewModel, nextType =

                    ViewScreens.AU1.name -> ViewRepository.AU1(checklistViewModel = checklistViewModel)
                    ViewScreens.AU2.name -> ViewRepository.AU2(checklistViewModel = checklistViewModel)

                    //TODO Improve AU3
                    ViewScreens.AU3.name -> ViewRepository.AU3(
                        viewModel = checklistViewModel,
                        nextType = 0
                    )

                    ViewScreens.PDF1.name -> ViewRepository.PDF1(checklistViewModel = checklistViewModel)

                    else -> {}

                }
            }
        }
    }
}

@Composable
private fun EndChecklistDialog(
    checklistViewModel: ChecklistViewModel,
    navController: NavController,
) {
    val buttonSize = 200

    CustomDialog(
        dialogSize = 0.75f,
        dismissRequest = { checklistViewModel.hideEndDialog() },
        title = "CHECKLIST FINALIZADA",
        centerActive = false,
        centerExists = false,
        leftText = "ACEPTAR",
        leftSize = buttonSize,
        leftIcon = R.drawable.correct_icon,
        leftColor = buttonOkColor,
        leftOnClick = {
            navController.navigate(STSScreens.MainScreen.name)
            checklistViewModel.hideEndDialog()
        },
        rightText = "VOLVER",
        rightSize = buttonSize,
        rightIcon = R.drawable.back,
        rightColor = buttonKoColor,
        rightOnClick = { checklistViewModel.hideEndDialog() },
        /*
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "¿Desea terminar la ejecución y enviar los resultados?",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.h4
                )
            }
        }
         */
    )
}