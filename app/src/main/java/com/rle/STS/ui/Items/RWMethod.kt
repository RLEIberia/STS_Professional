package com.rle.STS.ui.Items

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.os.Environment
import android.provider.MediaStore
import com.google.android.material.internal.ContextUtils

class RWMethod {


    @SuppressLint("RestrictedApi")
    fun VideoIntent(context: Context): Intent {
        val contentValues = ContentValues()
        contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, "my_video.mp4")
        contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "video/mp4")
        contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_MOVIES)
        val contentUri = ContextUtils.getActivity(context)!!.baseContext.contentResolver.insert(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues
        )
        val intent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri)
        intent.addFlags(REQUEST_ID)

        return intent
    }

    //Reproducir audio
    var ACTION_TTS = "com.realwear.ttsservice.intent.action.TTS"
    var ACTION_TTS_FINISHED = "com.realwear.ttsservice.intent.action.TTS_FINISHED"
    var EXTRA_TEXT = "text_to_speak"
    var EXTRA_ID = "tts_id"
    var EXTRA_PAUSE = "pause_speech_recognizer"
    val TTS_REQUEST_CODE = 34

    //Camara
    val REQUEST_ID = 123

    //Barcode
    // Request code identifying the barcode scanner events
    val BARCODE_REQUEST_CODE = 1984

    // Barcode scanner intent action
    val SCAN_BARCODE = "com.realwear.barcodereader.intent.action.SCAN_BARCODE"

    // Identifier for the result string returned by the barcode scanner
    val EXTRA_RESULT = "com.realwear.barcodereader.intent.extra.RESULT"

    //
    // Available barcode symbologies
    //
    val EXTRA_CODE_128 = "com.realwear.barcodereader.intent.extra.CODE_128"
    val EXTRA_CODE_DM = "com.realwear.barcodereader.intent.extra.CODE_DM"
    val EXTRA_CODE_EAN_UPC = "com.realwear.barcodereader.intent.extra.CODE_EAN_UPC"
    val EXTRA_CODE_QR = "com.realwear.barcodereader.intent.extra.CODE_QR"

    //Dictado
    // Request code identifying dictation events
    val DICTATION_REQUEST_CODE = 34

    // Dictation intent action
    val ACTION_DICTATION = "com.realwear.keyboard.intent.action.DICTATION"
}