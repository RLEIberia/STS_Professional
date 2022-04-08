package com.rle.STS.ui.Screens.ChecklistScreens.Data

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.FileProvider
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.VideoFrameDecoder
import coil.request.ImageRequest
import com.rle.STS.R
import com.rle.STS.ui.widgets.CustomButton
import java.io.File


@Composable
fun VideoScreen(file: String) {

    val context = LocalContext.current
    val videoPath: File = File(context.getFilesDir(), "Videos")
    val video = File(videoPath, file);

    Column {

        Row() {
            Spacer(modifier = Modifier.weight(1f))

            Card(
                modifier = Modifier
                    .width(450.dp)
                    .height(253.dp)
                    .clip(RoundedCornerShape(10))
                    .border(1.dp, Color.Black, RoundedCornerShape(10)),
            ) {
                AndroidView(
                    factory = { context: Context ->
                        val view = LayoutInflater.from(context)
                            .inflate(R.layout.imageview_layout, null, false)
                        val imageView = view.findViewById<ImageView>(R.id.imageView)

                        val imageLoader = ImageLoader.Builder(context)
                            .components {
                                add(VideoFrameDecoder.Factory())
                            }
                            .build()

                        val request = ImageRequest.Builder(context)
                            .data(video)
                            .crossfade(true)
                            .target(imageView)
                            .build()

                        imageLoader.enqueue(request)

                        view
                    },
                    modifier = Modifier
                        .width(450.dp)
                        .height(253.dp)
                        .clip(RoundedCornerShape(10)),
                )

            }

            Spacer(modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row() {

            Spacer(modifier = Modifier.weight(1f))

            CustomButton(
                text = stringResource(id = R.string.open_video),
                buttonSize = 180,
                onClick = {
                    val intent = Intent()
                    intent.setAction(Intent.ACTION_VIEW)
                    Log.d(
                        "FILE LOCATION:",
                        video.exists().toString()
                    ) // FileProvider.getUriForFile(context,context.packageName + ".provider", file).path!!)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        intent.setDataAndType(
                            FileProvider.getUriForFile(
                                context,
                                context.packageName + ".provider",
                                video
                            ), "video/*"
                        )
                    } else {
                        intent.setDataAndType(Uri.fromFile(video), "video/*")
                            .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    }
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    context.startActivity(intent)
                })

            Spacer(modifier = Modifier.weight(1f))

        }

    }

}