package com.rle.STS.utils.checklistUtils

import android.speech.tts.TextToSpeech
import android.util.Log

fun speak(audioText: CharSequence, tts: TextToSpeech) {
    try{
        tts.speak(audioText, TextToSpeech.QUEUE_FLUSH, null, "")
    } catch(e: Exception){
        Log.d("SPEAK", "AUDIO NO PRESENTE")
    }
}