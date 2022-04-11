package com.rle.STS.ui.Screens.ChecklistScreens.Data

import android.media.MediaPlayer
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.rle.STS.R
import com.rle.STS.ui.widgets.CustomButton
import java.io.File

@Composable
fun AudioScreen() {

    val context = LocalContext.current

    val imagePath: File = File(context.getFilesDir(), "Audios")
    val file = File(imagePath, "Grabacion.m4a");

    Column() {

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

    }

}