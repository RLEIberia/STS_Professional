package com.rle.STS.screens.scanner

import android.content.Intent
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.google.common.primitives.UnsignedBytes.toInt
import com.rle.STS.ActivityViewModel
import com.rle.STS.R
import com.rle.STS.items.RWMethod
import com.rle.STS.model.BBDD.ChecklistsTable
import com.rle.STS.model.BBDD.ExecutionsTable
import com.rle.STS.model.BBDD.FilesInTable
import com.rle.STS.model.BBDD.ProjectsTable
import com.rle.STS.model.JSON.checklistStructure.ChecklistData
import com.rle.STS.navigation.STSScreens
import com.rle.STS.screens.main.MainViewModel
import com.rle.STS.ui.theme.backgroundColor
import com.rle.STS.ui.theme.buttonStop
import com.rle.STS.ui.theme.specialButtonColor
import com.rle.STS.ui.theme.topBarColor
import com.rle.STS.utils.checklistUtils.openImage
import com.rle.STS.utils.checklistUtils.openPdf
import com.rle.STS.utils.checklistUtils.openVideo
import com.rle.STS.widgets.BottomBar
import com.rle.STS.widgets.TitleDialogBar
import java.text.SimpleDateFormat
import java.util.*


@Composable
fun ScannerScreen(
    navController: NavController,
    openScanner: MutableState<Boolean>,
    mainViewModel: MainViewModel,
    activityViewModel: ActivityViewModel,
    scannerViewModel: ScannerViewModel,
) {

    val result = remember { mutableStateOf<String?>("") }
    val rleCode = remember { mutableStateOf(false) }
    val type = remember { mutableStateOf("") }
    val id = remember { mutableStateOf("") }

    val checklistData = scannerViewModel.scannedChecklist.collectAsState()
    val projectData = scannerViewModel.scannedProject.collectAsState()
    val fileData = scannerViewModel.scannedFile.collectAsState()

    val context = LocalContext.current

    val intentBarCode = Intent(RWMethod.SCAN_BARCODE)
    intentBarCode.addFlags(RWMethod.BARCODE_REQUEST_CODE)

    val openViable = remember { mutableStateOf(false) }

    val launcherBarCode =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {

            //Siempre comenzamos pensando que no es
            rleCode.value = false

            val data = it.data
            if (data != null) {
                result.value = data.getStringExtra(RWMethod.EXTRA_RESULT)!!
                if (result.value!!.toString()
                        .split(" ", ignoreCase = true, limit = 3)[0] == "Rle2022"
                ) {
                    rleCode.value = true
                    type.value =
                        result.value.toString().split(" ", ignoreCase = true, limit = 3)[1]
                    id.value = result.value.toString().split(" ", ignoreCase = true, limit = 3)[2]

                }
            } else {
                openScanner.value = false
            }

            //Rle2022 CK aasdfasdfasdf
        }

    LaunchedEffect(key1 = true, block = {
        launcherBarCode.launch(intentBarCode)
    })
    if (rleCode.value)
        if(type.value == "CK" || type.value == "PJ" || type.value == "FL") {

            openViable.value = true

            when (type.value) {
                "CK" -> scannerViewModel.getChecklistById(Integer.parseInt(id.value.trim()))
                "PJ" -> scannerViewModel.getProjectById(Integer.parseInt(id.value.trim()))
                "FL" -> scannerViewModel.getFileById(Integer.parseInt(id.value.trim()))
            }
        }


    if (!result.value.isNullOrEmpty()) {
        ScannerDialog(
            type = type,
            checklistData = checklistData.value,
            projectData = projectData.value,
            fileData = fileData.value,
            openScanner = openScanner,
            openViable = openViable,
            leftFunction = { launcherBarCode.launch(intentBarCode) },
            title = when (type.value) {
                "CK" -> checklistData.value.name
                "PJ" -> projectData.value.name
                "FL" -> fileData.value.name
                else -> ""
            },
            centerFunction = {
                when(type.value){
                    "CK" -> {
                        activityViewModel.getChecklistById(Integer.parseInt(id.value.trim()))
                        activityViewModel.setExecution(ExecutionsTable())
                        navController.navigate(STSScreens.ChecklistScreen.name)
                    }
                    "PJ" -> {
                        activityViewModel.getProjectById(Integer.parseInt(id.value.trim()))
                        navController.navigate(STSScreens.ChecklistSelectScreen.name)
                    }
                    "FL" -> {
                        when(fileData.value.type){
                            0 -> openImage(
                                context = context,
                                file = fileData.value.file
                            )
                            1 -> openVideo(
                                context = context,
                                file = fileData.value.file
                            )
                            4 -> openPdf(
                                context = context,
                                file = fileData.value.file
                            )
                            else -> {}
                        }
                    }
                }
            }
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun ScannerDialog(
    checklistData: ChecklistsTable,
    projectData: ProjectsTable,
    fileData: FilesInTable,
    openScanner: MutableState<Boolean>,
    openViable: MutableState<Boolean>,
    type: MutableState<String>,
    leftFunction: () -> Unit = {},
    title: String = "",
    centerFunction: () -> Unit = {},
) {
    Dialog(
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
        ),
        onDismissRequest = {
            openScanner.value = false
        },
        content = {

            Scaffold(
                modifier = Modifier
                    .fillMaxSize(0.9f),
                topBar =
                {
                    TitleDialogBar(title)

                },
                content = {
                    when (type.value) {
                        "CK" ->
                            contentChecklist(
                                modifier = Modifier.padding(it),
                                checklistData = checklistData
                            )
                        "PJ" ->
                            contentProject(
                                modifier = Modifier.padding(it),
                                projectData = projectData
                            )
                        "FL" ->
                            contentFile(modifier = Modifier.padding(it), fileData = fileData)
                        else -> Surface(modifier = Modifier.fillMaxSize()) {
                            Column(
                                modifier = Modifier.fillMaxSize()
                                    .padding(it),
                                verticalArrangement = Arrangement.SpaceEvenly,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "ERROR",
                                    style = MaterialTheme.typography.h3,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "Código no reconocido.",
                                    style = MaterialTheme.typography.h5,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                        }
                    }
                },
                bottomBar = {
                    BottomBar(
                        modifierBottomBar = Modifier
                            .fillMaxWidth(),
                        centerText = stringResource(id = R.string.open_file),
                        centerIcon = R.drawable.file_icon,
                        centerExists = openViable.value,
                        centerOnClick = centerFunction,
                        centerSize = 220,
                        centerColor = specialButtonColor,
                        leftText = "LECTURA",
                        leftIcon = R.drawable.scan_qr,
                        leftOnClick = leftFunction,
                        leftSize = 220,
                        rightText = stringResource(id = R.string.go_back),
                        rightOnClick = { openScanner.value = false },
                        rightSize = 220,
                        rightColor = buttonStop
                    )
                }
            )
        },
    )
}


@Composable
fun contentChecklist(
    modifier: Modifier,
    checklistData: ChecklistsTable
) {
    val date = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    val letterSize = 25.sp

    Surface(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = checklistData.description,
                    fontSize = letterSize
                )
            }
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    fontSize = letterSize,
                    fontWeight = FontWeight.Bold,
                    text = "ID: "
                )
                Text(
                    fontSize = letterSize,
                    text = checklistData.id.toString()
                )
            }
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    fontSize = letterSize,
                    fontWeight = FontWeight.Bold,
                    text = "CREACIÓN: "

                )
                Text(
                    fontSize = letterSize,
                    fontWeight = FontWeight.Bold,
                    text = date.format(checklistData.created_at * 1000).toString()
                )
            }
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    fontSize = letterSize,
                    fontWeight = FontWeight.Bold,
                    text = "ACTUALIZACIÓN: "
                )
                Text(
                    fontSize = letterSize,
                    fontWeight = FontWeight.Bold,
                    text = date.format(checklistData.updated_at * 1000).toString()
                )
            }
        }
    }
}

@Composable
fun contentProject(
    modifier: Modifier,
    projectData: ProjectsTable
) {

    val date = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    val letterSize = 25.sp

    Surface(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = projectData.description,
                    fontSize = letterSize
                )
            }
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    fontSize = letterSize,
                    fontWeight = FontWeight.Bold,
                    text = "ID: "
                )
                Text(
                    fontSize = letterSize,
                    text = projectData.id.toString()
                )
            }
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    fontSize = letterSize,
                    fontWeight = FontWeight.Bold,
                    text = "CREACIÓN: "

                )
                Text(
                    fontSize = letterSize,
                    fontWeight = FontWeight.Bold,
                    text = date.format(projectData.created_at * 1000).toString()
                )
            }
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    fontSize = letterSize,
                    fontWeight = FontWeight.Bold,
                    text = "ACTUALIZACIÓN: "
                )
                Text(
                    fontSize = letterSize,
                    text = date.format(projectData.updated_at * 1000).toString()
                )
            }
        }
    }
}

@Composable
fun contentFile(
    modifier: Modifier,
    fileData: FilesInTable
) {

    val date = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    val letterSize = 25.sp

    Surface(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = fileData.description,
                    fontSize = letterSize
                )
            }
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    fontSize = letterSize,
                    fontWeight = FontWeight.Bold,
                    text = "TIPO: "
                )
                Text(
                    fontSize = letterSize,
                    text = when (fileData.type) {
                        0 -> "Imagen"
                        1 -> "Video"
                        2 -> "Audio"
                        4 -> "PDF"
                        else -> "No reconocido"
                    }
                )
            }
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    fontSize = letterSize,
                    fontWeight = FontWeight.Bold,
                    text = "ID: "
                )
                Text(
                    fontSize = letterSize,
                    text = fileData.id.toString()
                )
            }
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    fontSize = letterSize,
                    fontWeight = FontWeight.Bold,
                    text = "CREACIÓN: "

                )
                Text(
                    fontSize = letterSize,
                    text = date.format(fileData.date * 1000).toString()
                )
            }
        }
    }
}