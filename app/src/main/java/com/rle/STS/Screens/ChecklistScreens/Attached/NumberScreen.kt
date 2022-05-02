package com.rle.STS.Screens.ChecklistScreens.Attached

import android.annotation.SuppressLint
import android.content.Context
import android.text.InputType
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.widget.addTextChangedListener
import com.rle.STS.R
import com.rle.STS.Screens.ChecklistScreens.CheckListStepViewModel
import com.rle.STS.widgets.CustomButton
import com.rle.STS.widgets.defaultStepBottomButtons

@SuppressLint("RestrictedApi")
@Composable
fun NumberScreen(check: () -> Unit, stepViewModel: CheckListStepViewModel, nextType: Int) {

    val numero = remember { mutableStateOf("") }
    val openKeyboard = remember { mutableStateOf(false) }

    Column {

        Spacer(modifier = Modifier.weight(1f))

        Row() {

            Spacer(modifier = Modifier.weight(1f))

            AndroidView(
                factory = { context: Context ->
                    val view = LayoutInflater.from(context)
                        .inflate(R.layout.edit_text_layout, null, false)
                    val editText = view.findViewById<EditText>(R.id.editText)
                    editText.setText(numero.value)
                    editText.addTextChangedListener {
                        numero.value = it.toString()
                    }
                    editText.setOnEditorActionListener { textView, actionId, keyEvent ->
                        if(actionId== EditorInfo.IME_ACTION_DONE){
                            editText.clearFocus();
                        }
                        false;
                    }

                    editText.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
                    //editText.setInputType(InputType.TYPE_CLASS_NUMBER)

                    view
                },
                update = { view ->
                    val editText = view.findViewById<EditText>(R.id.editText)
                    if (openKeyboard.value && !editText.hasFocus()) {
                        editText.requestFocus()
                    }else if (!openKeyboard.value && editText.hasFocus()){
                        editText.clearFocus()
                    }
                }
            )

            Spacer(modifier = Modifier.weight(1f))

        }

        Spacer(modifier = Modifier.height(20.dp))

        Row() {
            Spacer(modifier = Modifier.weight(1f))

            CustomButton(
                text = stringResource(id = R.string.introduce_number),
                buttonSize = 150,
                onClick = {
                    openKeyboard.value = true
                }
            )

            Spacer(modifier = Modifier.width(10.dp))

            CustomButton(
                text = "Limpiar",
                onClick = { numero.value = "" },
                buttonSize = 150
            )

            Spacer(modifier = Modifier.weight(1f))

        }

        Spacer(modifier = Modifier.weight(1f))

        Spacer(modifier = Modifier.height(10.dp))

        if (numero.value == ""){
            defaultStepBottomButtons(stepViewModel, hasValue = false, nextType = nextType)
        } else {
            defaultStepBottomButtons(stepViewModel, hasValue = true, nextType = nextType)
        }

        Spacer(modifier = Modifier.height(10.dp))

    }

}