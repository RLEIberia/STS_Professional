package com.rle.STS.screens.viewScreens.data

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rle.STS.R
import com.rle.STS.viewModel.ChecklistViewModel
import com.rle.STS.screens.viewScreens.utils.DescriptionRow
import com.rle.STS.ui.theme.buttonOkColor
import com.rle.STS.utils.checklistUtils.openPdf
import com.rle.STS.widgets.CustomSideIconButton
import java.io.File


@Composable
fun PDFScreen(
    checklistViewModel: ChecklistViewModel
) {

    val context = LocalContext.current

    //ViewModel variables
    val currentStep = checklistViewModel.currentStep.collectAsState()
    val currentView = checklistViewModel.currentView.collectAsState()
    val viewData =
        checklistViewModel.checklist.collectAsState().value.checklistData!!.steps[currentStep.value]
            .views[currentView.value].viewData

//    //Internal variables
//    val currentTime = remember {
//        mutableStateOf(0)
//    }
//    val fullTime = remember {
//        mutableStateOf(0)
//    }

    //Directory variables
    val directoryStr = context.filesDir.path + File.separator + "PDF" + File.separator + "In"
    val directory = File(directoryStr)
    //Check if directory exists, if not create it
    if (!directory.exists())
        directory.mkdirs()

    //Audio
    val audioFile = File(directory, viewData.files[0].file)

    //MediaPlayer
//    val mutableMediaPlayer = remember {
//        mutableStateOf(MediaPlayer())
//    }
//    var mp: MediaPlayer = MediaPlayer()

    //Check if AudioFile exists
//    if (audioFile.exists()) {
//        mutableMediaPlayer.value = MediaPlayer.create(context, Uri.fromFile(audioFile))
//    }

    val audioState = remember {
        mutableStateOf(false)
    }

    val audioPosition = remember {
        mutableStateOf(0)
    }
//    if(audioState.value){
//
//        LaunchedEffect(Unit) {
//            while (audioState.value) {
//                Log.d("LOOP", mp.currentPosition.toString())
//                audioPosition.value = mp.currentPosition
//                delay(500)
//            }
//        }
//    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            DescriptionRow(viewData = viewData)

            Row(
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(220.dp)
                    .padding(5.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    Card(
                        modifier = Modifier
                            .height(140.dp),
                        shape = RoundedCornerShape(10),
                        elevation = 5.dp
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                    Text(text = "Abra el archivo...")
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxSize(),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ){
//                                Text(
//                                    text = "${mutableMediaPlayer.value.currentPosition} / ${mutableMediaPlayer.value.duration}",
//                                    fontSize = 25.sp,
//                                    fontWeight = FontWeight.SemiBold
//                                )
                                Text(
                                    text = viewData.files[0].file,
                                    fontSize = 25.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }

                    }

//                    Spacer(modifier = Modifier.size(5.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center

                    ) {

                        CustomSideIconButton(
                            text = stringResource(id = R.string.open_file),
                            onClick = {
                                openPdf(file = viewData.files[0].file, context = context)
                            },
                            buttonColor = buttonOkColor,
                            buttonSize = 220,
                            icon = R.drawable.file_icon
                        )

                        //TODO STOP WHEN CHANGE

                        //Play/Pause Button
//                        CustomSideIconButton(
//                            text = when (audioState.value) {
//                                false -> stringResource(id = R.string.play)
//                                true -> stringResource(id = R.string.pause)
//                            },
//                            onClick = {
//                                when (audioState.value) {
//                                    false -> {
//                                        mutableMediaPlayer.value.start()
//                                    }
//                                    true -> mutableMediaPlayer.value.pause()
//                                }
//                                audioState.value = mutableMediaPlayer.value.isPlaying
//                            },
//                            buttonColor = when (audioState.value) {
//                                false -> buttonOkColor
//                                true -> grayedButton
//                            },
//                            buttonSize = 220,
//                            icon = when (audioState.value) {
//                                false -> R.drawable.play
//                                true -> R.drawable.pause
//                            },
//                        )
//
//                        //Stop Button
//                        CustomSideIconButton(
//                            text = stringResource(id = R.string.stop),
//                            onClick = {
//                                mutableMediaPlayer.value.pause()
//                                mutableMediaPlayer.value.seekTo(0)
//                                audioState.value = mutableMediaPlayer.value.isPlaying
//                            },
//                            buttonColor = buttonStop,
//                            buttonSize = 220,
//                            icon = R.drawable.stop
//                        )


                    }

                }

            }

        }


    }


}