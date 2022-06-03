package com.rle.STS.screens.viewScreens.data

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import com.rle.STS.R
import com.rle.STS.screens.checklist.ChecklistViewModel
import com.rle.STS.widgets.CustomButton
import com.rle.STS.widgets.VideoThumbnail
import com.rle.STS.widgets.defaultStepBottomButtons
import java.io.File


@Composable
fun VideoScreen(checklistViewModel: ChecklistViewModel) {


    val context = LocalContext.current

    //ViewModel variables
    val currentStep = checklistViewModel.currentStep.collectAsState()
    val currentView = checklistViewModel.currentView.collectAsState()
    val viewData =
        checklistViewModel.checklist.collectAsState().value.checklistData!!.steps[currentStep.value]
            .views[currentView.value].viewData

    //File variables
    val directoryStr = context.filesDir.path + File.separator + "Videos" + File.separator + "In"
    val directory = File(directoryStr)
    val videoFile = File(directory, viewData.files[0].file)

    //Test if the directory exists, if not, create it
    if (!directory.exists())
        directory.mkdirs()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Row() {

            Card(
                modifier = Modifier
                    .clip(RoundedCornerShape(5)),
                elevation = 10.dp
            ) {

                VideoThumbnail(video = videoFile)
            }
        }
    }
}
