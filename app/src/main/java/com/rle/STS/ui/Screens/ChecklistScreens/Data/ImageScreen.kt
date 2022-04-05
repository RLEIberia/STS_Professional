package com.rle.STS.ui.Screens.ChecklistScreens.Data

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import coil.compose.rememberAsyncImagePainter
import java.io.File

@Composable
fun ImageScreen(file: String) {

    val context = LocalContext.current

    val imagePath: File = File(context.getFilesDir(), "Images")
    val file = File(imagePath, file);

    Column() {

        Spacer(modifier = Modifier.height(10.dp))

        Row() {

            Spacer(modifier = Modifier.weight(1f))

            Image(
                rememberAsyncImagePainter(file),
                contentDescription = "hf_scroll_horizontal|hf_scroll_vertical",
                modifier = Modifier
                    .width(350.dp)
                    .height(350.dp)
                    .fillMaxSize()
            )

            Spacer(modifier = Modifier.weight(1f))

        }

        Spacer(modifier = Modifier.height(10.dp))

        Row() {

            Spacer(modifier = Modifier.weight(1f))

            Button(onClick = {
                val intent = Intent()
                intent.setAction(Intent.ACTION_VIEW)
                Log.d(
                    "FILE LOCATION:",
                    file.exists().toString()
                ) // FileProvider.getUriForFile(context,context.packageName + ".provider", file).path!!)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    intent.setDataAndType(
                        FileProvider.getUriForFile(
                            context,
                            context.packageName + ".provider",
                            file
                        ), "image/*"
                    )
                } else {
                    intent.setDataAndType(Uri.fromFile(file), "image/*")
                        .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                }
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                context.startActivity(intent)
            }) {
                Text(text = "Ver Imagen")
            }

            Spacer(modifier = Modifier.weight(1f))

        }

        Spacer(modifier = Modifier.height(10.dp))


    }

}