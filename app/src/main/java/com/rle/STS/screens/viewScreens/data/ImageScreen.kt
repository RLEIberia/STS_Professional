package com.rle.STS.screens.viewScreens.data

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import coil.compose.rememberAsyncImagePainter
import com.rle.STS.R
import com.rle.STS.screens.checklist.CheckListStepScreen
import com.rle.STS.screens.checklist.ChecklistViewModel
import com.rle.STS.ui.theme.STSTheme
import com.rle.STS.screens.widgets.CustomButton
import com.rle.STS.screens.widgets.defaultStepBottomButtons
import java.io.File

@Composable
fun ImageScreen(file: String, type: Int, stepViewModel: ChecklistViewModel) {

    val context = LocalContext.current

    val imagePath = File(context.getFilesDir(), "Images")
    val file = File(imagePath, file);

    var borderWidth = 1
    var borderColor = Color.Black

    if (type == 1) {
        borderWidth = 1
        borderColor = Color.Green
    } else if (type == 2) {
        borderWidth = 1
        borderColor = Color.Red
    }

    Column() {

        Spacer(modifier = Modifier.weight(1f))

        Row() {

            Spacer(modifier = Modifier.weight(1f))

            Box(
                modifier = Modifier
                    .width(450.dp)
                    .height(253.dp),
            ) {
                Column() {
                    Spacer(modifier = Modifier.weight(1f))
                    Row() {
                        Spacer(modifier = Modifier.weight(1f))
                        Image(
                            rememberAsyncImagePainter(file),
                            contentDescription = "",
                            modifier = Modifier
                                .clip(RoundedCornerShape(10))
                                .border(borderWidth.dp, borderColor, RoundedCornerShape(10)),
                        )
                        Spacer(modifier = Modifier.weight(1f))
                    }
                    Spacer(modifier = Modifier.weight(1f))
                }
                if (type == 1)
                    Icon(
                        Icons.Filled.Check,
                        contentDescription = "",
                        tint = Color.Green,
                        modifier = Modifier.size(50.dp)
                    )
                else if (type == 2) {
                    Icon(
                        Icons.Filled.Clear,
                        contentDescription = "",
                        tint = Color.Red,
                        modifier = Modifier.size(50.dp)

                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

        }

        Spacer(modifier = Modifier.height(10.dp))

        Row() {

            Spacer(modifier = Modifier.weight(1f))

            CustomButton(
                text = stringResource(id = R.string.open_image),
                buttonSize = 180,
                onClick = {
                    val intent = Intent()
                    intent.setAction(Intent.ACTION_VIEW)
                    Log.d(
                        "FILE LOCATION:",
                        file.exists().toString()
                    )
                    intent.setDataAndType(
                        FileProvider.getUriForFile(
                            context,
                            context.packageName + ".provider",
                            file
                        ), "image/*"
                    )
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    context.startActivity(intent)
                }
            )

            Spacer(modifier = Modifier.weight(1f))

        }

        Spacer(modifier = Modifier.weight(1f))

        Spacer(modifier = Modifier.height(10.dp))

        defaultStepBottomButtons(stepViewModel)

        Spacer(modifier = Modifier.height(10.dp))

    }

}


//@Preview(showBackground = true, widthDp = 851, heightDp = 480)
//@Composable
//private fun DefaultPreview() {
//    STSTheme {
//        CheckListStepScreen()
//    }
//}