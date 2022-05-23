package com.rle.STS.widgets

import android.content.Context
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import coil.ImageLoader
import coil.decode.VideoFrameDecoder
import coil.request.ImageRequest
import com.rle.STS.R
import java.io.File


@Composable
fun VideoThumbnail(video: File){

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
            .clip(RoundedCornerShape(5)),
    )

}