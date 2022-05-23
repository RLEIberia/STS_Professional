package com.rle.STS.screens.viewScreens.data

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import coil.compose.rememberAsyncImagePainter
import com.rle.STS.R
import com.rle.STS.screens.checklist.ChecklistViewModel
import com.rle.STS.widgets.CustomButton
import com.rle.STS.widgets.defaultStepBottomButtons
import java.io.File
import java.util.*

@Composable
fun ImageScreen(type: Int, checklistViewModel: ChecklistViewModel) {

    val context = LocalContext.current

    val currentStep = checklistViewModel.currentStep.collectAsState()
    val currentView = checklistViewModel.currentView.collectAsState()
    val viewData =
        checklistViewModel.checklist.collectAsState().value.checklistData!!.steps[currentStep.value]
            .views[currentView.value].viewData

    val imagePath = File(context.getFilesDir(), "Images")

    val directoryStr = context.filesDir.path + File.separator + "Images" + File.separator + "In"

    val directory = File(directoryStr)

    if (!directory.exists())
        directory.mkdirs()

    val imageFile = File(directory, viewData.files[0].file);

    var borderWidth = 1
    var borderColor = Color.Black

    if (type == 0){
        borderWidth = 0
        borderColor = Color.Transparent
    }
    else if (type == 1) {
        borderWidth = 10
        borderColor = Color.Green
    } else if (type == 2) {
        borderWidth = 10
        borderColor = Color.Red
    }



    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Card(
            modifier = Modifier
                .border(10.dp, borderColor, RoundedCornerShape(5)),
            elevation = 10.dp,
            shape = RoundedCornerShape(5)
        ){
            Box(

            ) {

                Image(
                    rememberAsyncImagePainter(imageFile),
                    contentDescription = "",
                    modifier = Modifier
                        .clip(RoundedCornerShape(5))
                        .border(borderWidth.dp, borderColor, RoundedCornerShape(5)),
                )


                Row(horizontalArrangement = Arrangement.End) {
                    if (type == 1)
                        Box(
                            modifier = Modifier
                                .size(80.dp)
                                .border(10.dp, borderColor, RoundedCornerShape(10))
                                .background(borderColor),
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Icon(
                                    Icons.Filled.Check,
                                    contentDescription = "",
                                    tint = Color.White,
                                    modifier = Modifier.size(50.dp)
                                )
                            }
                        }
                    else if (type == 2) {
                        Box(
                            modifier = Modifier
                                .size(80.dp)
                                .border(10.dp, borderColor, RoundedCornerShape(10))
                                .background(borderColor)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Icon(
                                    Icons.Filled.Clear,
                                    contentDescription = "",
                                    tint = Color.White,
                                    modifier = Modifier.size(50.dp)
                                )
                            }
                        }
                    }
                }

            }
        }


        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {



        }


    }

}


//@Preview(showBackground = true, widthDp = 851, heightDp = 480)
//@Composable
//private fun DefaultPreview() {
//    STSTheme {
//        CheckListStepScreen()
//    }
//}