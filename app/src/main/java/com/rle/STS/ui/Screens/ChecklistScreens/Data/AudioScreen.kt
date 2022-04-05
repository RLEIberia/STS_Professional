package com.rle.STS.ui.Screens.ChecklistScreens.Data

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.rle.STS.ui.widgets.BottomButtons
import java.io.File

@Composable
fun AudioScreen() {

    val context = LocalContext.current

    val imagePath: File = File(context.getFilesDir(), "Audios")
    val file = File(imagePath, "Grabacion.m4a");

    Column() {

        Spacer(modifier = Modifier.weight(1f))


        Row() {
            Spacer(modifier = Modifier.weight(1f))

            val uri = Uri.fromFile(file)
            val mp: MediaPlayer = MediaPlayer.create(context, uri)
            Button(onClick = { mp.start() }) {
                Text(text = "Reproducir audio")
            }

            Spacer(modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.weight(1f))

    }

}


@Composable
fun playAudio(context: Context) {


}