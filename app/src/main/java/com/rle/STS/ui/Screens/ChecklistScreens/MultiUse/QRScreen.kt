package com.rle.STS.ui.Screens.ChecklistScreens.Data

import android.content.Intent
import android.graphics.Color
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.rle.STS.ui.Items.RWMethod

@Composable
fun QRScreen() {
    val context = LocalContext.current

    val RWMethod = RWMethod()

    val intent = Intent(RWMethod.SCAN_BARCODE)

    //
    // Set which symbologies are enabled. If none is specified, all are enabled by default
    //
    intent.putExtra(RWMethod.EXTRA_CODE_128, false)
    intent.putExtra(RWMethod.EXTRA_CODE_DM, true)
    intent.putExtra(RWMethod.EXTRA_CODE_EAN_UPC, true)
    intent.putExtra(RWMethod.EXTRA_CODE_QR, true)
    intent.addFlags(RWMethod.BARCODE_REQUEST_CODE)

    val result = remember { mutableStateOf<String?>(null) }
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {

            val data = it.data
            result.value = "[No Barcode]"
            if ( data != null) {
                result.value = data.getStringExtra(RWMethod.EXTRA_RESULT)!!
            }
        }

    Column() {

        Spacer(modifier = Modifier.weight(1f))

        val resultPrefix = "Resultado: "

        Row() {
            Spacer(modifier = Modifier.weight(1f))

            Button(onClick = {

                launcher.launch(intent)

            }) {
                Text(text = "Leer QR")
            }

            Spacer(modifier = Modifier.weight(1f))
        }

        Row() {
            Spacer(modifier = Modifier.weight(1f))

            if (result.value != null) {
                Text(text = resultPrefix + result.value)
            }

            Spacer(modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.weight(1f))

    }

}