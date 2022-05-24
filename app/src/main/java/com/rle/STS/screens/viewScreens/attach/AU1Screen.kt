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
import androidx.compose.ui.text.font.FontWeight
import com.rle.STS.R
import com.rle.STS.items.RWMethod
import com.rle.STS.screens.checklist.ChecklistViewModel

@Composable
fun AU1Screen(checklistViewModel: ChecklistViewModel){

    val result = remember { mutableStateOf<String?>("") }
    val context = LocalContext.current

    val intentDictate = Intent(RWMethod.ACTION_DICTATION)

    val launcherDictate =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {

            val data = it.data
            if (data != null) {
                result.value = data.getStringExtra("result")
            }
        }

    TextFieldIntroduce(
        checklistViewModel = checklistViewModel,
        pendingText = "Pendiente de dictado...",
        finishedText = "Dictado finalizado.",
        buttonFunction = {launcherDictate.launch(intentDictate)},
        buttonText = stringResource(id = R.string.dictate),
        buttonColor = Color(0xFF3BCE8E),
        buttonIcon = R.drawable.dictate_icon,
        buttonSize = 180,
        result = result.value.toString(),
        textResultSize = 25,
        textResultWeight = FontWeight.Normal
    )

}