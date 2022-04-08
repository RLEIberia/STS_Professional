package com.rle.STS.ui.Screens.ChecklistScreens.Data

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import coil.compose.rememberAsyncImagePainter
import com.rle.STS.R
import com.rle.STS.ui.widgets.CustomButton
import java.io.File

@Composable
fun ImageScreen(file: String) {

    val context = LocalContext.current

    val imagePath = File(context.getFilesDir(), "Images")
    val file = File(imagePath, file);

    Column() {

        Row() {

            Spacer(modifier = Modifier.weight(1f))

            Card(
                modifier = Modifier
                    .width(450.dp)
                    .height(253.dp)
                    .clip(RoundedCornerShape(10))
                    .border(1.dp, Color.Black, RoundedCornerShape(10)),
            ) {
                Image(
                    rememberAsyncImagePainter(file),
                    contentDescription = "hf_scroll_horizontal|hf_scroll_vertical",
                    modifier = Modifier
                        .width(500.dp)
                        .height(250.dp)
                        .fillMaxSize()
                )
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

    }

}