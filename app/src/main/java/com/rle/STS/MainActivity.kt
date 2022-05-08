package com.rle.STS

import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.KeyEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.rle.STS.items.RWMethod
import com.rle.STS.navigation.STSNavigation
import com.rle.STS.ui.theme.STSTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)


        setContent {

            STSApp()

            /*Button(onClick = {
                //textToSpeechEngine.speak("Probando el texto a dialogo", TextToSpeech.QUEUE_FLUSH, null, "tts1")
                //textToSpeechRealWear()
            }) {
                Text(text = "Prueba")
            }*/

        }
    }


    //TODO REVISAR FUNCIONES

    //Text to Speech RealWear
    private val EXTRA_TEXT = "text_to_speak"
    private val EXTRA_ID = "tts_id"
    private val EXTRA_PAUSE = "pause_speech_recognizer"

    private val TTS_REQUEST_CODE = 34

    private fun textToSpeechRealWear() {
        val speech = "Selecciona la imagen correcta"
        val intent = Intent(RWMethod.ACTION_TTS)
        intent.putExtra(EXTRA_TEXT, speech)
        intent.putExtra(EXTRA_ID, TTS_REQUEST_CODE)
        intent.putExtra(EXTRA_PAUSE, false)
        sendBroadcast(intent)
    }

    //Text to Speech Android
    private val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val spokenText: String? =
                result.data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    .let { text -> text?.get(0) }
            Log.d("TEST",spokenText.toString())
        }
    }

    private val textToSpeechEngine: TextToSpeech by lazy {
        TextToSpeech(this) {
            if (it == TextToSpeech.SUCCESS) textToSpeechEngine.language = Locale("es_ES")
        }
    }


    //Para que funcione hay que añadir la siguiente contentDescription a algun elemento de la pantalla: hf_no_ptt_home
    private val ActionBtnKeyCode = 500
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        when (keyCode) {
            ActionBtnKeyCode ->                     // Action key is down - return true to stop default behavior
            {
                onBackPressed()
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        when (keyCode) {
            ActionBtnKeyCode ->                     // Action key has been released - return true to stop default behavior
            {
                onBackPressed()
                return true
            }
        }
        return super.onKeyUp(keyCode, event)
    }

}

@Composable
fun STSApp() {

    STSTheme() {

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                STSNavigation()
            }
        }
    }

}


fun Context.getActivity(): AppCompatActivity? {
    var currentContext = this
    while (currentContext is ContextWrapper) {
        if (currentContext is AppCompatActivity) {
            return currentContext
        }
        currentContext = currentContext.baseContext
    }
    return null
}


