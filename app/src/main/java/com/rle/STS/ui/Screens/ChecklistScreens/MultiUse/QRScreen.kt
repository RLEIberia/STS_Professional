package com.rle.STS.ui.Screens.ChecklistScreens.Data

import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.rle.STS.R
import com.rle.STS.ui.Items.RWMethod
import com.rle.STS.ui.widgets.CustomButton

@Composable
fun QRScreen(task : () -> Unit) {

    val RWMethod = RWMethod()

    val intent = Intent(RWMethod.SCAN_BARCODE)

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
            if ( data != null) {
                result.value = data.getStringExtra(RWMethod.EXTRA_RESULT)!!
            }
        }

    Column() {

        Row() {
            Spacer(modifier = Modifier.weight(1f))

            CustomButton(
                text = stringResource(id = R.string.read_qr),
                buttonSize = 150,
                onClick = { launcher.launch(intent) }
            )

            Spacer(modifier = Modifier.weight(1f))
        }

        Row() {
            Spacer(modifier = Modifier.weight(1f))

            if (result.value != null) {
                task()
                //Text(text = stringResource(id = R.string.result, result.value!!))
            }

            Spacer(modifier = Modifier.weight(1f))
        }

    }

}