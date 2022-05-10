package com.rle.STS.screens.utils

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.rle.STS.R
import com.rle.STS.widgets.BottomButtons
import com.rle.STS.widgets.CustomSideIconButton

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ListConfirmDialog(
    openConfirmDialog: MutableState<Boolean>,
    clickedList: MutableState<String>,
    onListClick: () -> Unit,
) {

    //Confirmar checklist

    AlertDialog(
        shape = RoundedCornerShape(3),
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),
        modifier = Modifier
            .fillMaxSize(0.8f)
            .border(
                BorderStroke(
                    width = 3.dp,
                    color = Color.Black,

                ),
                shape = RoundedCornerShape(3)
            ),
        onDismissRequest = {
            openConfirmDialog.value = false
        },
        text = {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    //TODO -> Recibir viewModel con la información

                    Text(
                        //Título checklist
                        modifier = Modifier
                            .padding(start = 30.dp, end = 5.dp),
                        text = "Checklist 1",
                        style = MaterialTheme.typography.h4,
                        fontWeight = FontWeight.Bold
                    )

                    Row(
                        modifier = Modifier. fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(
                            text = "Checklist, cuya traducción literal en español es lista de verificación, es un método de control que relaciona diversas tareas.",
                            style = MaterialTheme.typography.h6
                        )
                    }
                }
            }
        },
        buttons = {
            Row(
                modifier = Modifier
                    .padding(all = 8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                CustomSideIconButton(
                    text = stringResource(id = R.string.cancel),
                    onClick = { openConfirmDialog.value = false },
                    icon = R.drawable.cancel_icon,
                    buttonColor = Color.Red,
                    buttonSize = 220
                )
                CustomSideIconButton(
                    text = stringResource(id = R.string.confirm),
                    onClick = { onListClick() },
                    icon = R.drawable.correct_icon,
                    buttonColor = Color.Green,
                    buttonSize = 220
                )
//                BottomButtons(
//                    leftFunction = { openConfirmDialog.value = false },
//                    leftText = stringResource(id = R.string.cancel),//ROJO
//                    rightFunction = {
//                        onListClick()
//                    },
//                    rightText = stringResource(id = R.string.confirm)//VERDE
//                )
            }
        }
    )
}