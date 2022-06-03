package com.rle.STS.screens.viewScreens.result

import android.Manifest
import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.rle.STS.R
import com.rle.STS.items.Checklist
import com.rle.STS.screens.viewScreens.attach.createAudioFile
import com.rle.STS.screens.checklist.ChecklistViewModel
import com.rle.STS.widgets.CustomButton
import java.io.File

@Composable
fun OKKOScreen(checklistViewModel: ChecklistViewModel) {

    val currentStep = checklistViewModel.currentStep.collectAsState()
    val currentView = checklistViewModel.currentView.collectAsState()
    val viewPersistence = checklistViewModel.viewPersistenceListFlow.observeAsState(emptyList())
    val stepPersistence = checklistViewModel.stepPersistenceFlow.observeAsState(emptyList())
    val viewData =
        checklistViewModel.checklist.collectAsState().value.checklistData!!.steps[currentStep.value]
            .views[currentView.value].viewData

    val executionData = checklistViewModel.executionData.observeAsState(emptyList())

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

    if (!viewPersistence.value.isNullOrEmpty()) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                style = MaterialTheme.typography.h4,
                text = viewData.text[0],
                textAlign = TextAlign.Center
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                CustomButton(
                    buttonSize = 160,
                    text = stringResource(id = R.string.correct),
                    onClick = {
                        //estado.value = 1
                        checklistViewModel.viewUpdate(
                            previousViewData = viewPersistence.value[currentView.value],
                            result = Checklist.CORRECT.toString()
                        )
                        checklistViewModel.stepUpdate(
                            previousStepData = stepPersistence.value[0],
                            result_code = Checklist.CORRECT
                        )
                        checklistViewModel.next(previousExecutionData = executionData.value[0], delay = 500)
                    },
                    buttonColor = (
                        if (viewPersistence.value[currentView.value].result == Checklist.CORRECT.toString()) {
                            Color.Green
                        } else {
                            Color.Gray
                        }
                    )
                )
                CustomButton(
                    buttonSize = 160,
                    text = stringResource(id = R.string.nsnc),
                    onClick = {
                        checklistViewModel.viewUpdate(
                            previousViewData = viewPersistence.value[currentView.value],
                            result = Checklist.IGNORE.toString()
                        )
                        checklistViewModel.stepUpdate(
                            previousStepData = stepPersistence.value[0],
                            result_code = Checklist.IGNORE
                        )
                        checklistViewModel.next(previousExecutionData = executionData.value[0], delay = 500)
                    },
                    buttonColor = (
                        if (viewPersistence.value[currentView.value].result == Checklist.IGNORE.toString()) {
                            Color.Blue
                        } else {
                            Color.Gray
                        }
                    )
                )
                CustomButton(
                    buttonSize = 160,
                    text = stringResource(id = R.string.incorrect),
                    onClick = {
                        checklistViewModel.viewUpdate(
                            previousViewData = viewPersistence.value[currentView.value],
                            result = Checklist.INCORRECT.toString()
                        )
                        checklistViewModel.stepUpdate(
                            previousStepData = stepPersistence.value[0],
                            result_code = Checklist.INCORRECT
                        )
                        checklistViewModel.next(previousExecutionData = executionData.value[0], delay = 500)
                    },
                    buttonColor = (
                        if (viewPersistence.value[currentView.value].result == Checklist.INCORRECT.toString()) {
                            Color.Red
                        } else {
                            Color.Gray
                        }
                    )
                )
            }
        }
    }
}