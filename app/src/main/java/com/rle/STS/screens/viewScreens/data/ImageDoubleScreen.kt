package com.rle.STS.screens.viewScreens.data

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.rle.STS.viewModel.ChecklistViewModel
import java.io.File


@Composable
fun ImageDoubleScreen(type: Int, checklistViewModel: ChecklistViewModel) {


    val context = LocalContext.current

    val currentStep = checklistViewModel.currentStep.collectAsState()
    val currentView = checklistViewModel.currentView.collectAsState()
    val viewData =
        checklistViewModel.checklist.collectAsState().value.checklistData!!.steps[currentStep.value]
            .views[currentView.value].viewData


    val directoryStr = context.filesDir.path + File.separator + "Images" + File.separator + "In"

    val directory = File(directoryStr)

    if (!directory.exists())
        directory.mkdirs()

    val imageFile = File(directory, viewData.files[0].file);

    var borderWidth = 1
    var borderColor = Color.Black

    if (type == NORMAL) {
        borderWidth = 0
        borderColor = Color.Transparent
    } else if (type == CORRECT) {
        borderWidth = 10
        borderColor = Color.Green
    } else if (type == INCORRECT) {
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
        ) {
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
                    if (type == 1 || type == 2)
                        Box(
                            modifier = Modifier
                                .size(80.dp)
                                .border(
                                    borderWidth.dp,
                                    borderColor,
                                    RoundedCornerShape(10)
                                )
                                .background(borderColor),
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Icon(
                                    when (type) {
                                        CORRECT -> Icons.Filled.Check
                                        INCORRECT -> Icons.Filled.Clear
                                        else -> Icons.Filled.AccountCircle
                                    },
                                    contentDescription = "",
                                    tint = Color.White,
                                    modifier = Modifier.size(50.dp)
                                )
                            }
                        }
                    /*
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
                    */
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