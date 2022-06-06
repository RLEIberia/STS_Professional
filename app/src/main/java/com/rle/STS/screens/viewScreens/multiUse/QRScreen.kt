package com.rle.STS.screens.viewScreens.data

import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.rle.STS.R
import com.rle.STS.items.RWMethod
import com.rle.STS.viewModel.ChecklistViewModel
import com.rle.STS.widgets.CustomButton


@Composable
fun QRScreen(
    type: Int,
    check: () -> Boolean,
    checklistViewModel: ChecklistViewModel,
) {

    val intent = Intent(RWMethod.SCAN_BARCODE)

    val context = LocalContext.current

    val openDialog = remember { mutableStateOf(false) }

    //
    // Set which symbologies are enabled. If none is specified, all are enabled by default
    //
    /*intent.putExtra(RWMethod.EXTRA_CODE_128, false)
    intent.putExtra(RWMethod.EXTRA_CODE_DM, true)
    intent.putExtra(RWMethod.EXTRA_CODE_EAN_UPC, true)
    intent.putExtra(RWMethod.EXTRA_CODE_QR, true)*/
    intent.addFlags(RWMethod.BARCODE_REQUEST_CODE)

    val result = remember { mutableStateOf<String?>("") }
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {

            val data = it.data
            if (data != null) {
                result.value = data.getStringExtra(RWMethod.EXTRA_RESULT)!!
            }
        }

    Column {


        Text(
            modifier = Modifier
                .fillMaxWidth(),
            style = MaterialTheme.typography.h4,
            text = "Haz la lectura de un código QR.",
            textAlign = TextAlign.Center
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (result.value == "") {
                Text(text = "QR sin leer", color = Color.Gray, style = MaterialTheme.typography.h5)
                //Text(text = "Mas informacion")
            } else {
                if (type == 0) {
                    Text(text = "QR leido correctamente", color = Color.Green , style = MaterialTheme.typography.h5)
                } else {
                    if (check()) {
                        Text(text = "QR leido correctamente", color = Color.Green)
                    } else {
                        Text(text = "QR leido incorrectamente", color = Color.Green)
                    }

                }
            }

        }



        Row() {

            CustomButton(
                text = stringResource(id = R.string.read_qr),
                buttonSize = 180,
                onClick = { launcher.launch(intent) }
            )

        }



        if (type == 2 || type == 3) {
            Row() {

                CustomButton(
                    text = "Informacion",
                    onClick = { if (result.value != "") openDialog.value = true },
                    buttonSize = 180
                )

            }
        }



    }

    if (openDialog.value) {

        ShowDataDialog(openDialog = openDialog, text = result.value!!)

    }

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