package com.rle.STS.screens.viewScreens.data

import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.rle.STS.R
import com.rle.STS.screens.checklist.ChecklistViewModel
import com.rle.STS.widgets.CustomButton


@Composable
fun QRScreen(
    type: Int,
    check: () -> Boolean,
    stepViewModel: ChecklistViewModel,
    nextType: Int
) {
//
//    val intent = Intent(RWMethod.SCAN_BARCODE)
//
//    val context = LocalContext.current
//
//    val openDialog = remember { mutableStateOf(false) }
//
//    //
//    // Set which symbologies are enabled. If none is specified, all are enabled by default
//    //
//    /*intent.putExtra(RWMethod.EXTRA_CODE_128, false)
//    intent.putExtra(RWMethod.EXTRA_CODE_DM, true)
//    intent.putExtra(RWMethod.EXTRA_CODE_EAN_UPC, true)
//    intent.putExtra(RWMethod.EXTRA_CODE_QR, true)*/
//    intent.addFlags(RWMethod.BARCODE_REQUEST_CODE)
//
//    val result = remember { mutableStateOf<String?>("") }
//    val launcher =
//        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
//
//            val data = it.data
//            if (data != null) {
//                result.value = data.getStringExtra(RWMethod.EXTRA_RESULT)!!
//            }
//        }
//
//    Column {
//
//        Spacer(modifier = Modifier.weight(1f))
//
//        Text(
//            modifier = Modifier
//                .fillMaxWidth(),
//            style = MaterialTheme.typography.h4,
//            text = "Haz la lectura de un código QR.",
//            textAlign = TextAlign.Center
//        )
//
//        Spacer(modifier = Modifier.height(20.dp))
//
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            if (result.value == "") {
//                Text(text = "QR sin leer", color = Color.Gray, style = MaterialTheme.typography.h5)
//                //Text(text = "Mas informacion")
//            } else {
//                if (type == 0) {
//                    Text(text = "QR leido correctamente", color = Color.Green , style = MaterialTheme.typography.h5)
//                } else {
//                    if (check()) {
//                        Text(text = "QR leido correctamente", color = Color.Green)
//                    } else {
//                        Text(text = "QR leido incorrectamente", color = Color.Green)
//                    }
//
//                }
//            }
//
//        }
//
//        Spacer(modifier = Modifier.height(20.dp))
//
//
//        Row() {
//            Spacer(modifier = Modifier.weight(1f))
//
//            CustomButton(
//                text = stringResource(id = R.string.read_qr),
//                buttonSize = 180,
//                onClick = { launcher.launch(intent) }
//            )
//
//            Spacer(modifier = Modifier.weight(1f))
//        }
//
//        Spacer(modifier = Modifier.weight(1f))
//
//        Spacer(modifier = Modifier.height(20.dp))
//
//        if (type == 2 || type == 3) {
//            Row() {
//                Spacer(modifier = Modifier.weight(1f))
//
//                CustomButton(
//                    text = "Informacion",
//                    onClick = { if (result.value != "") openDialog.value = true },
//                    buttonSize = 180
//                )
//
//                Spacer(modifier = Modifier.weight(1f))
//            }
//        }
//
//        Spacer(modifier = Modifier.weight(1f))
//
//        Spacer(modifier = Modifier.height(10.dp))
//
//        val checkListPosition = stepViewModel.checkListPosition.collectAsState()
//        val checkListSize = stepViewModel.checkListSize.collectAsState()
//
//        BottomButtons(
//            leftFunction = {
//                if (checkListPosition.value > 0) {
//                    stepViewModel.setPosition(checkListPosition.value - 1)
//                }
//            },
//            rightFunction = {
//                var increaseChecklist = true
//
//                if (checkListPosition.value >= checkListSize.value - 1) {
//                    increaseChecklist = false
//                } else {
//                    //finalizar checklist
//                }
//
//                if (result.value != "Hola") { // Comprobar si es valor esperado
//                    increaseChecklist = false
//                    stepViewModel.setConfirmDialog(true)
//                }
//
//                if (increaseChecklist || nextType == 0) {
//                    stepViewModel.setPosition(checkListPosition.value + 1)
//                } else if (nextType == 1) {
//                    stepViewModel.setConfirmDialog(true)
//                } else {
//
//                }
//            }
//        ) // Manejar botones desde aqui para cargar siguiente vista correctamente mediante metodo de lectura de JSON
//
//        Spacer(modifier = Modifier.height(10.dp))
//
//    }
//
//    if (openDialog.value) {
//
//        ShowDataDialog(openDialog = openDialog, text = result.value!!)
//
//    }

}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ShowDataDialog(
    openDialog: MutableState<Boolean>,
    text: String
) {

    AlertDialog(
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),
        modifier = Modifier
            .wrapContentSize(),
        onDismissRequest = {
            openDialog.value = false
        },
        text = {
            Row() {

                Text(text = text)

            }
        },
        buttons = {
            Row(
                modifier = Modifier.padding(all = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                CustomButton(
                    text = stringResource(id = R.string.close),
                    onClick = { openDialog.value = false })

                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    )
}