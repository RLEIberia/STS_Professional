package com.rle.STS.utils.checklistUtils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.core.content.FileProvider
import com.rle.STS.BuildConfig
import java.io.File

fun openVideo(
    file: String,
    context: Context
) {

    val directoryStr = context.filesDir.path + File.separator + "Videos" + File.separator + "In"
    val directory = File(directoryStr)
    val videoFile = File(directory, file)

    if (!directory.exists())
        directory.mkdirs()
    val intent = Intent()

    intent.setAction(Intent.ACTION_VIEW)

    intent.setDataAndType(
        FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".provider", videoFile),
        "video/*"
    )
    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    context.startActivity(intent)


}