package com.rle.STS.ui.Screens.ChecklistScreens.Attached

import android.Manifest.permission.RECORD_AUDIO
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.rle.STS.ui.widgets.CustomButton
import java.io.File
import java.io.IOException
import java.lang.Exception


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RecordAudioScreen() {

    val context = LocalContext.current

    Column() {

        Spacer(modifier = Modifier.weight(1f))


        Row() {

            val launcher = rememberLauncherForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { isGranted: Boolean ->
                if (isGranted) {
                    // Permission Accepted: Do something
                    Log.d("ExampleScreen", "PERMISSION GRANTED")

                } else {
                    // Permission Denied: Do something
                    Log.d("ExampleScreen", "PERMISSION DENIED")
                }
            }

            var mediaRecorder = MediaRecorder()


            when (PackageManager.PERMISSION_GRANTED) {
                ContextCompat.checkSelfPermission(
                    context,
                    RECORD_AUDIO
                ) -> {

                }
                else -> {
                    // Asking for permission
                    SideEffect {
                        launcher.launch(RECORD_AUDIO)
                    }
                }
            }

            var recordingStarted = false

            Spacer(modifier = Modifier.weight(1f))

            CustomButton(text = "Detener grabacion", onClick = {

                Log.d("test", "stop")

                try {
                    mediaRecorder.stop() //Parar de grabar
                    mediaRecorder.reset()
                    mediaRecorder.release()
                } catch (e: IOException) {
                    Log.e("MEDIA_RECORDER ERROR", "stop() failed")
                    e.printStackTrace()
                }
                recordingStarted = false
            })

            //Spacer(modifier = Modifier.width(20.dp))

            Spacer(modifier = Modifier.weight(1f))


            CustomButton(text = "Reproducir grabacion", onClick = {

                Log.d("test", "stop")

                val filePath: File = File(context.getFilesDir(), "Audios/grabaciones")
                val file = File(filePath, "test.mp3")
                if (file.exists()){
                    val uri = Uri.fromFile(file)
                    val mp: MediaPlayer = MediaPlayer.create(context, uri)
                    mp.start()
                }
                recordingStarted = false
            })

            //Spacer(modifier = Modifier.width(20.dp))

            Spacer(modifier = Modifier.weight(1f))


            CustomButton(text = "Comenzar grabacion", onClick = {
                Log.d("test", "start")

                try {
                    mediaRecorder = MediaRecorder()
                    mediaRecorder.reset()

                    // Some works that require permission
                    Log.d("AUDIO RECORD", "Inicializado")
                    mediaRecorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT)
                    mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_2_TS)
                    mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
                    val filePath: File = File(context.getFilesDir(), "Audios/grabaciones")
                    val file = File(filePath, "test.mp3")
                    file.createNewFile()
                    mediaRecorder.setOutputFile(file)
                    mediaRecorder.prepare()


                    mediaRecorder.start() //Empezar a grabar
                } catch (e: IOException) {
                    Log.e("MEDIA_RECORDER ERROR", "prepare() failed")
                    e.printStackTrace()
                }
                recordingStarted = true
            })


            Spacer(modifier = Modifier.weight(1f))

        }

        Spacer(modifier = Modifier.weight(1f))

    }

}