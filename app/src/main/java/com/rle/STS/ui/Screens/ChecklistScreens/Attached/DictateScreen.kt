package com.rle.STS.ui.Screens.ChecklistScreens.Attached

import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rle.STS.R
import com.rle.STS.ui.Items.RWMethod
import com.rle.STS.ui.Screens.ChecklistScreens.CheckListStepViewModel
import com.rle.STS.ui.widgets.BottomButtons
import com.rle.STS.ui.widgets.CustomButton
import com.rle.STS.ui.widgets.defaultStepBottomButtons

@Composable
fun DictateScreen(stepViewModel: CheckListStepViewModel, nextType: Int) {


    val intent = Intent(RWMethod.ACTION_DICTATION)
    val result = remember { mutableStateOf<String?>("") }
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {

            val data = it.data
            if ( data != null) {
                result.value = data.getStringExtra("result")//methods.EXTRA_RESULT)!!
            }
        }


    Column {

        Spacer(modifier = Modifier.weight(1f))

        Row() {
            Spacer(modifier = Modifier.weight(1f))

            Text(text = result.value.toString()) // ELEMENTO DEL PASO

            Spacer(modifier = Modifier.weight(1f))
        }


        Row() {
            Spacer(modifier = Modifier.weight(1f))

            CustomButton(
                text = stringResource(id = R.string.dictate),
                onClick = { launcher.launch(intent) }
            )

            Spacer(modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.weight(1f))

        Spacer(modifier = Modifier.height(10.dp))

        if (result.value == ""){
            defaultStepBottomButtons(stepViewModel, hasValue = false, nextType = nextType)
        } else {
            defaultStepBottomButtons(stepViewModel, hasValue = true, nextType = nextType)
        }

        Spacer(modifier = Modifier.height(10.dp))

    }

}