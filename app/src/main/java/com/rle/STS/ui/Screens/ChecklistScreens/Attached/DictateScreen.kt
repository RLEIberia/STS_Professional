package com.rle.STS.ui.Screens.ChecklistScreens.Attached

import android.content.Intent
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
import com.rle.STS.ui.Items.RWMethod

@Composable
fun DictateScreen() {


    val methods = RWMethod()
    val intent = Intent(methods.ACTION_DICTATION)
    val result = remember { mutableStateOf<String?>("no text") }
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {

            val data = it.data
            result.value = "[No Dictation]"
            if ( data != null) {
                result.value = data.getStringExtra(methods.EXTRA_RESULT)!!
            }
        }


    Column() {

        Spacer(modifier = Modifier.weight(1f))


        Row() {
            Spacer(modifier = Modifier.weight(1f))

            Text(text = result.value.toString()) // ELEMENTO DEL PASO

            Spacer(modifier = Modifier.weight(1f))
        }


        Row() {
            Spacer(modifier = Modifier.weight(1f))

            Button(onClick = {

                launcher.launch(intent)

            }) {
                Text(text = "Dictar")
            }

            Spacer(modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.weight(1f))

    }

}