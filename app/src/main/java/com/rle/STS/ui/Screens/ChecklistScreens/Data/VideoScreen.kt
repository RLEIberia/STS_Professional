package com.rle.STS.ui.Screens.ChecklistScreens.Data

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.LocalImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.decode.VideoFrameDecoder
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.request.ImageRequest
import coil.request.videoFrameMillis
import com.rle.STS.ui.widgets.BottomButtons
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.File


@Composable
fun VideoScreen(file: String) {

    val context = LocalContext.current
    val imagePath: File = File(context.getFilesDir(), "Videos")
    val file = File(imagePath, file);

    Column {

        Spacer(modifier = Modifier.height(10.dp))


        Row() {
            Spacer(modifier = Modifier.weight(1f))

            Image(
                painter = rememberAsyncImagePainter(file),
                contentDescription = "",
                Modifier.height(100.dp).width(100.dp)
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
                        ), "video/*"
                    )
                } else {
                    intent.setDataAndType(Uri.fromFile(file), "video/*")
                        .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                }
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                context.startActivity(intent)
            }) {
                Text(text = "Ver Video")
            }

            Spacer(modifier = Modifier.weight(1f))

        }

    }

}