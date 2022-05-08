package com.rle.STS.screens.utils

import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.rle.STS.R
import com.rle.STS.screens.widgets.BottomButtons

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ListConfirmDialog(
    openConfirmDialog: MutableState<Boolean>,
    clickedList: MutableState<String>,
    onListClick: () -> Unit,
) {

    //Confirmar checklist

    AlertDialog(
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),
        modifier = Modifier
            .width(450.dp),
        onDismissRequest = {
            openConfirmDialog.value = false
        },
        text = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                Text(text = clickedList.value, fontSize = 30.sp)

            }
        },
        buttons = {
            Row(
                modifier = Modifier.padding(all = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                BottomButtons(
                    leftFunction = { openConfirmDialog.value = false },
                    leftText = stringResource(id = R.string.cancel),//ROJO
                    rightFunction = {
                        onListClick()
                    },
                    rightText = stringResource(id = R.string.confirm)//VERDE
                )
            }
        }
    )
}