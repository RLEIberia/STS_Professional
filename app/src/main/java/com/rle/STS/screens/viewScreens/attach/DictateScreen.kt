package com.rle.STS.screens.viewScreens.attach

import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.rle.STS.R
import com.rle.STS.items.RWMethod
import com.rle.STS.viewModel.ChecklistViewModel
import com.rle.STS.widgets.CustomButton
import com.rle.STS.widgets.defaultStepBottomButtons

@Composable
fun DictateScreen(
    stepViewModel: ChecklistViewModel,
    nextType: Int,
    audioText: String? = ""
) {


    val intent = Intent(RWMethod.ACTION_DICTATION)
    val result = remember { mutableStateOf<String?>("") }



    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {

            val data = it.data
            if (data != null) {
                result.value = data.getStringExtra("result")//methods.EXTRA_RESULT)!!
            }
        }

//    LaunchedEffect(
//        key1 = null,
//        block = {
//
//            tts(audioText, ApplicationContext)
//
//        }
//    )


    Column {

        Spacer(modifier = Modifier.weight(1f))

        Text(
            modifier = Modifier
                .fillMaxWidth(),
            style = MaterialTheme.typography.h4,
            text = "Realiza una prueba de dictado",
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.weight(1f))

        if(result.value.isNullOrEmpty()){}
        else{
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = result.value.toString(),
                    style = MaterialTheme.typography.h5
                ) // ELEMENTO DEL PASO

            }
        }

        Spacer(modifier = Modifier.weight(1f))


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

        if (result.value == "") {
            defaultStepBottomButtons(stepViewModel, hasValue = false, nextType = nextType)
        } else {
            defaultStepBottomButtons(stepViewModel, hasValue = true, nextType = nextType)
        }

        Spacer(modifier = Modifier.height(10.dp))

    }

}