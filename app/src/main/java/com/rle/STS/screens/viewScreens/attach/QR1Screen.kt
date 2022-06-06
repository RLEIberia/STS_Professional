package com.rle.STS.screens.viewScreens.attach

import android.content.Intent
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.rle.STS.R
import com.rle.STS.items.RWMethod
import com.rle.STS.viewModel.ChecklistViewModel

@Composable
fun QR1Screen(checklistViewModel: ChecklistViewModel){

    val result = remember { mutableStateOf<String?>("") }
    val context = LocalContext.current

    val currentStep = checklistViewModel.currentStep.collectAsState()
    val currentView = checklistViewModel.currentView.collectAsState()
    val viewPersistence = checklistViewModel.viewPersistenceListFlow.observeAsState(emptyList())

    val intentBarCode = Intent(RWMethod.SCAN_BARCODE)
    intentBarCode.addFlags(RWMethod.BARCODE_REQUEST_CODE)

    val launcherBarCode =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {

            val data = it.data
            if (data != null) {
                //result.value = data.getStringExtra(RWMethod.EXTRA_RESULT)!!
                checklistViewModel.viewUpdate(
                    previousViewData = viewPersistence.value[currentView.value],
                    result = data.getStringExtra(RWMethod.EXTRA_RESULT)!!
                )
            }
        }

    if(!viewPersistence.value.isNullOrEmpty()) {

        Log.d("VIEW_PER", ": ${viewPersistence.value[currentView.value]}")

        TextFieldIntroduce(
            checklistViewModel = checklistViewModel,
            pendingText = "Pendiente de lectura...",
            finishedText = "Lectura finalizada.",
            buttonFunction = { launcherBarCode.launch(intentBarCode) },
            buttonText = stringResource(id = R.string.scanner),
            buttonColor = Color(0xFF3BCE8E),
            buttonIcon = R.drawable.scan_qr,
            buttonSize = 500,
            result = viewPersistence.value[currentView.value].result
            //result.value.toString()
        )
    }

}