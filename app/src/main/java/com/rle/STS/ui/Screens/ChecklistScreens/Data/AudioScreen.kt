package com.rle.STS.ui.Screens.ChecklistScreens.Data

import android.media.MediaPlayer
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rle.STS.R
import com.rle.STS.ui.Screens.ChecklistScreens.CheckListStepViewModel
import com.rle.STS.ui.widgets.BottomButtons
import com.rle.STS.ui.widgets.CustomButton
import java.io.File

@Composable
fun AudioScreen(fileName: String, stepViewModel: CheckListStepViewModel) {

    val context = LocalContext.current

    val imagePath: File = File(context.getFilesDir(), "Audios")
    val file = File(imagePath, fileName);

    Column() {

        Spacer(modifier = Modifier.weight(1f))

        Row() {
            Spacer(modifier = Modifier.weight(1f))

            val uri = Uri.fromFile(file)
            val mp: MediaPlayer = MediaPlayer.create(context, uri)
            CustomButton(
                text = stringResource(id = R.string.play_audio),
                buttonSize = 180,
                onClick = { mp.start() }
            )

            Spacer(modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.weight(1f))

        Spacer(modifier = Modifier.height(10.dp))

        val checkListPosition = stepViewModel.getPosition()
        val checkListSize = stepViewModel.getSize()

        BottomButtons(leftFunction = {
            if (checkListPosition.value!! > 0) {
                stepViewModel.setPosition(checkListPosition.value!! - 1)
            }
        }, rightFunction = {
            if (checkListPosition.value!! >= checkListSize.value!! - 1) {
                //Terminar checklist
            } else {
                stepViewModel.setPosition(checkListPosition.value!! + 1)
            }
        }) // Manejar botones desde aqui para cargar siguiente vista correctamente mediante metodo de lectura de JSON

        Spacer(modifier = Modifier.height(10.dp))

    }

}