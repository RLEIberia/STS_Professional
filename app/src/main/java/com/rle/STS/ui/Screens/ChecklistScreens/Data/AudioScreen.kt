package com.rle.STS.ui.Screens.ChecklistScreens.Data

import android.media.MediaPlayer
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rle.STS.R
import com.rle.STS.ui.Screens.ChecklistScreens.CheckListStepViewModel
import com.rle.STS.ui.widgets.BottomButtons
import com.rle.STS.ui.widgets.CustomButton
import com.rle.STS.ui.widgets.defaultStepBottomButtons
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

        defaultStepBottomButtons(stepViewModel)

        Spacer(modifier = Modifier.height(10.dp))

    }

}