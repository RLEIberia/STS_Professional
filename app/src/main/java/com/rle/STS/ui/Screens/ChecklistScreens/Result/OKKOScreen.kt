package com.rle.STS.ui.Screens.ChecklistScreens.Result

import android.Manifest
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.net.Uri
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.rle.STS.R
import com.rle.STS.ui.Screens.ChecklistScreens.Attached.createAudioFile
import com.rle.STS.ui.Screens.ChecklistScreens.CheckListStepViewModel
import com.rle.STS.ui.widgets.BottomButtons
import com.rle.STS.ui.widgets.CustomButton
import com.rle.STS.ui.widgets.defaultStepBottomButtons
import java.io.File
import java.io.IOException
import java.lang.Exception

@Composable
fun OKKOScreen(stepViewModel: CheckListStepViewModel, nextType: Int) {

    var B1Enabled = remember { true }
    var B2Enabled = remember { true }
    var B3Enabled = remember { true }

    val context = LocalContext.current

    val project = "1"
    val checklist = "1"
    val user = "user"

    val directoryS =
        context.filesDir.path + File.separator + "Audios" + File.separator + "recordings"

    val directory = File(directoryS)

    val file = remember {
        createAudioFile(context, project, checklist, user)
    }

    for (audio in directory.listFiles()!!) {
        if (audio.length() == 0L && !audio.equals(file)) {
            audio.delete()
        }
    }

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // Permission Accepted: Do something
            Log.d("RecordAudioScreen", "PERMISSION GRANTED")

        } else {
            // Permission Denied: Do something
            Log.d("RecordAudioScreen", "PERMISSION DENIED")
        }
    }

    val mediaRecorder = remember {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            MediaRecorder(context)
        } else {
            MediaRecorder()
        }
    }


    when (PackageManager.PERMISSION_GRANTED) {
        ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.RECORD_AUDIO
        ) -> {

        }
        else -> {
            // Asking for permission
            SideEffect {
                launcher.launch(Manifest.permission.RECORD_AUDIO)
            }
        }
    }


    val recordingStarted = remember { mutableStateOf(false) }

    val textRecording =
        remember { mutableStateOf(context.getString(R.string.start_recording)) }


    Column() {

        Spacer(modifier = Modifier.weight(1f))


        Row() {
            Spacer(modifier = Modifier.weight(1f))
            CustomButton(
                buttonSize = 160,
                text = stringResource(id = R.string.correct),
                onClick = {
                    B1Enabled = true
                    B2Enabled = false
                    B3Enabled = false
                }
            )
            Spacer(modifier = Modifier.weight(1f))
            CustomButton(
                buttonSize = 160,
                text = stringResource(id = R.string.nsnc),
                onClick = {
                    B1Enabled = true
                    B2Enabled = false
                    B3Enabled = false
                }
            )
            Spacer(modifier = Modifier.weight(1f))
            CustomButton(
                buttonSize = 160,
                text = stringResource(id = R.string.incorrect),
                onClick = {
                    B1Enabled = true
                    B2Enabled = false
                    B3Enabled = false
                }
            )
            Spacer(modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.weight(1f))

        Row() {

            Spacer(modifier = Modifier.weight(1f))

            CustomButton(
                text = textRecording.value,
                buttonSize = 180,
                onClick = {

                    if (recordingStarted.value) {

                        try {
                            mediaRecorder.stop()
                            mediaRecorder.reset()
                            mediaRecorder.release()
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                        recordingStarted.value = false
                        textRecording.value = context.getString(R.string.start_recording)

                    } else {

                        try {
                            mediaRecorder.reset()

                            // Some works that require permission
                            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT)
                            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_2_TS)
                            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
                            mediaRecorder.setOutputFile(file)
                            mediaRecorder.prepare()


                            mediaRecorder.start() //Empezar a grabar
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                        recordingStarted.value = true
                        textRecording.value = context.getString(R.string.stop_recording)

                    }
                }
            )

            Spacer(modifier = Modifier.weight(1f))


            CustomButton(
                text = stringResource(id = R.string.play_recording),
                buttonSize = 180,
                onClick = {
                    if (recordingStarted.value) {

                        Toast.makeText(
                            context,
                            context.getText(R.string.is_recording),
                            Toast.LENGTH_SHORT
                        ).show()

                    } else {

                        if (file.exists()) {
                            val uri = Uri.fromFile(file)
                            val mp: MediaPlayer = MediaPlayer.create(context, uri)
                            mp.start()
                        }

                    }
                }
            )

            Spacer(modifier = Modifier.weight(1f))

        }

        Spacer(modifier = Modifier.weight(1f))

        Spacer(modifier = Modifier.height(10.dp))

        val oneSelected = B1Enabled || B2Enabled || B3Enabled
        if (oneSelected){
            defaultStepBottomButtons(stepViewModel, hasValue = true, nextType = nextType)
        } else {
            defaultStepBottomButtons(stepViewModel, hasValue = false, nextType = nextType)
        }

        Spacer(modifier = Modifier.height(10.dp))
    }



}