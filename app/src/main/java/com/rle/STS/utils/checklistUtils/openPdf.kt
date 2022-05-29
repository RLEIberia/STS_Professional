package com.rle.STS.utils.checklistUtils

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.core.content.FileProvider
import com.rle.STS.model.JSON.checklistStructure.ChecklistJSON
import com.rle.STS.screens.checklist.ChecklistViewModel
import java.io.File


fun openPdf(
    file: String,
    context: Context
){


    val directoryStr = context.filesDir.path + File.separator + "PDF" + File.separator + "In"
    val directory = File(directoryStr)
    val imageFile = File(directory, file)


    val intent = Intent()
    intent.setAction(Intent.ACTION_VIEW)
    Log.d(
        "FILE LOCATION:",
        imageFile.exists().toString()
    )
    intent.setDataAndType(
        FileProvider.getUriForFile(
            context,
            context.packageName + ".provider",
            imageFile
        ), "application/pdf"
    )
    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    context.startActivity(intent)

}