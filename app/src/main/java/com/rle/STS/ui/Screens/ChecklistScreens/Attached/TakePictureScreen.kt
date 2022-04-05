package com.rle.STS.ui.Screens.ChecklistScreens.Attached

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.google.android.material.internal.ContextUtils.getActivity
import com.rle.STS.ui.Items.RWMethod
import com.rle.STS.ui.Screens.ChecklistScreens.CheckListStepScreen
import com.rle.STS.ui.theme.CheckListaApplicationTheme
import com.rle.STS.ui.widgets.CustomButton
import java.io.File

@SuppressLint("RestrictedApi")
@Composable
fun TakePictureScreen() {

    val context = LocalContext.current

    //
    // Set which symbologies are enabled. If none is specified, all are enabled by default
    //

    val bitmap = remember { mutableStateOf<Bitmap?>(null) }
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) {
            bitmap.value = it
        }

    Column() {

        Spacer(modifier = Modifier.height(10.dp))

        Row() {

            Column() {
                Row() {
                    Spacer(modifier = Modifier.width(40.dp))

                    bitmap.let {
                        val data = it.value
                        if (data != null) {
                            Image(
                                bitmap = data.asImageBitmap(),
                                contentDescription = null,
                                modifier = Modifier
                                    .width(450.dp)
                                    .height(260.dp)
                                    .clip(RoundedCornerShape(10)),
                            )
                        } else {
                            Icon(
                                painter = painterResource(id = com.rle.STS.R.drawable.ic_baseline_image_24),
                                contentDescription = null,
                                modifier = Modifier
                                    .width(450.dp)
                                    .height(260.dp)
                                    .clip(RoundedCornerShape(10)),
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(20.dp))
                }
            }


            Column() {

                CustomButton(text = "Ver lista", onClick = { launcher.launch() }, buttonSize = 150)

                Card() {
                    Text(
                        text = "0",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                    )
                }

            }

        }

        Spacer(modifier = Modifier.height(10.dp))

        Row() {
            Spacer(modifier = Modifier.weight(1f))

            CustomButton(text = "Tomar foto", onClick = { launcher.launch() }, buttonSize = 200)

            Spacer(modifier = Modifier.weight(1f))
        }

    }


}


@Preview(showBackground = true, widthDp = 851, heightDp = 480)
@Composable
private fun DefaultPreview() {
    CheckListaApplicationTheme {
        TakePictureScreen()
    }
}