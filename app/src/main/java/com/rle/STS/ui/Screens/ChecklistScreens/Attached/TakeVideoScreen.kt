package com.rle.STS.ui.Screens.ChecklistScreens.Attached

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.google.android.material.internal.ContextUtils
import com.rle.STS.ui.Items.RWMethod
import java.io.File

@SuppressLint("RestrictedApi")
@Composable
fun TakeVideoScreen() {

    val context = LocalContext.current

    val RWMethod = RWMethod()

    //
    // Set which symbologies are enabled. If none is specified, all are enabled by default
    //

    val intent = RWMethod.VideoIntent(context)

    val result = remember { mutableStateOf<Uri?>(null) }
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {

            val data = it.data
            Log.d("Test", data.toString())
            if (data != null) {
                result.value = data.data
            }

        }

    Column() {

        Spacer(modifier = Modifier.weight(1f))

        Row() {
            Spacer(modifier = Modifier.weight(1f))
            val file = File(result.value.toString());

            Column() {

                Spacer(modifier = Modifier.weight(1f))

                Row() {
                    Spacer(modifier = Modifier.weight(1f))
                    Box(modifier = Modifier
                        .width(300.dp)
                        .height(300.dp)) {
                        Image(
                            painter = rememberAsyncImagePainter(file),
                            contentDescription = ""
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                }

                Row() {
                    Spacer(modifier = Modifier.weight(1f))

                    Button(onClick = {

                        launcher.launch(intent)

                    }) {
                        Text(text = "Grabar video") // ELEMENTO DEL PASO
                    }

                    Spacer(modifier = Modifier.weight(1f))
                }

            }

            Spacer(modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.weight(1f))

    }



}