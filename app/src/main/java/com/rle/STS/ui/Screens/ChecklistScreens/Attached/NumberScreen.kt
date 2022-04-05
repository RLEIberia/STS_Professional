package com.rle.STS.ui.Screens.ChecklistScreens.Attached

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.google.android.material.internal.ContextUtils.getActivity
import java.io.File

@SuppressLint("RestrictedApi")
@Composable
fun NumberScreen() {

    val context = LocalContext.current


    var numero = remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }

    Row() {

        Spacer(modifier = Modifier.weight(1f))

        Column() {

            Spacer(modifier = Modifier.weight(1f))

            Row() {
                Spacer(modifier = Modifier.weight(1f))

                TextField(
                    value = numero.value,
                    onValueChange = { numero.value = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .focusRequester(focusRequester)
                        .onFocusChanged { Log.d("FOCUS", "CHANGED") }
                )

                Spacer(modifier = Modifier.weight(1f))
            }

            Row() {
                Spacer(modifier = Modifier.weight(1f))

                Button(onClick = {

                    focusRequester.requestFocus()

                }) {
                    Text(text = "Introducir numero")
                }

                Spacer(modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.weight(1f))

        }

        Spacer(modifier = Modifier.weight(1f))

    }

}