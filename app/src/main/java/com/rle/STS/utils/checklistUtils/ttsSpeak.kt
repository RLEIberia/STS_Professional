package com.rle.STS.utils.checklistUtils

import android.speech.tts.TextToSpeech
import android.util.Log
import kotlinx.coroutines.delay

suspend fun speak(
    audioText: CharSequence,
    tts: TextToSpeech,
    switch: Boolean,
    delay: Long
) {
    if(switch) {
        tts.stop()
        try {
            delay(delay)
            tts.speak(audioText, TextToSpeech.QUEUE_FLUSH, null, "")
        } catch (e: Exception) {
            Log.d("SPEAK", "AUDIO NO PRESENTE")
        }
    }
}