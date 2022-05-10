package com.rle.STS.utils

import android.content.Context
import android.content.Intent
import com.rle.STS.items.RWMethod

//TODO REVISAR FUNCIONES

//Text to Speech RealWear
private val EXTRA_TEXT = "text_to_speak"
private val EXTRA_ID = "tts_id"
private val EXTRA_PAUSE = "pause_speech_recognizer"

private val TTS_REQUEST_CODE = 34


fun tts(text: String, context: Context) {

    val speech = text
    val intent = Intent(RWMethod.ACTION_TTS)
    intent.putExtra(EXTRA_TEXT, speech)
    intent.putExtra(EXTRA_ID, TTS_REQUEST_CODE)
    intent.putExtra(EXTRA_PAUSE, true)

}