package com.rle.STS.screens.viewScreens.attach

import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.rle.STS.R
import com.rle.STS.items.RWMethod
import com.rle.STS.screens.checklist.ChecklistViewModel

@Composable
fun QR1Screen(checklistViewModel: ChecklistViewModel){

    val result = remember { mutableStateOf<String?>("") }
    val context = LocalContext.current

    val intentBarCode = Intent(RWMethod.SCAN_BARCODE)
    intentBarCode.addFlags(RWMethod.BARCODE_REQUEST_CODE)

    val launcherBarCode =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {

            val data = it.data
            if (data != null) {
                result.value = data.getStringExtra(RWMethod.EXTRA_RESULT)!!
            }
        }

    TextFieldIntroduce(
        checklistViewModel = checklistViewModel,
        pendingText = "Pendiente de lectura...",
        finishedText = "Lectura finalizada.",
        buttonFunction = {launcherBarCode.launch(intentBarCode)},
        buttonText = stringResource(id = R.string.scanner),
        buttonColor = Color(0xFF3BCE8E),
        buttonIcon = R.drawable.scan_qr,
        buttonSize = 180,
        result = result.value.toString()
    )

}