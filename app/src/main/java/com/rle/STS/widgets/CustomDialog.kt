package com.rle.STS.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.rle.STS.R
import com.rle.STS.ui.theme.buttonsColor

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CustomDialog(

    modifier: Modifier = Modifier,
    dialogSize: Float = 0.8f,
    dismissRequest: () -> Unit = {},
    title: String = "",
    //content: @Composable()() -> Unit,

    topBar: @Composable()() -> Unit = {TitleDialogBar(title = title)},

    //BottomBar
    leftActive: Boolean = true,
    leftExists: Boolean = true,
    leftText: String = "LEFT",
    leftIcon: Int = R.drawable.options,
    leftOnClick: () -> Unit = {},
    leftColor: Color = buttonsColor,
    leftSize: Int = 220,
    centerActive: Boolean = true,
    centerExists: Boolean = true,
    centerText: String = "LEFT",
    centerIcon: Int = R.drawable.options,
    centerOnClick: () -> Unit = {},
    centerColor: Color = buttonsColor,
    centerSize: Int = 220,
    rightActive: Boolean = true,
    rightExists: Boolean = true,
    rightText: String = "LEFT",
    rightIcon: Int = R.drawable.options,
    rightOnClick: () -> Unit = {},
    rightColor: Color = buttonsColor,
    rightSize: Int = 220,


) {
    Dialog(
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),
        onDismissRequest = dismissRequest,
        content = {
            Scaffold(
                modifier = Modifier
                    .fillMaxSize(dialogSize),
                topBar = {
                    TitleDialogBar(title = title)
                },
                content = {
                    Surface(
                        modifier = Modifier
                            .padding(it)
                            .fillMaxSize()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "¿Desea terminar la ejecución y enviar los resultados?",
                                fontWeight = FontWeight.SemiBold,
                                style = MaterialTheme.typography.h5
                            )
                        }
                    }
                },
                bottomBar = {
                    BottomBar(
                        modifierBottomBar = Modifier
                            .fillMaxWidth(),
                        leftActive = leftActive,
                        leftExists = leftExists,
                        leftText = leftText,
                        leftIcon = leftIcon,
                        leftOnClick = leftOnClick,
                        leftColor = leftColor,
                        leftSize = leftSize,
                        centerActive = centerActive,
                        centerExists = centerExists,
                        centerText = centerText,
                        centerIcon = centerIcon,
                        centerOnClick = centerOnClick,
                        centerColor = centerColor,
                        centerSize = centerSize,
                        rightActive = rightActive,
                        rightExists = rightExists,
                        rightText = rightText,
                        rightIcon = rightIcon,
                        rightOnClick = rightOnClick,
                        rightColor = rightColor,
                        rightSize = rightSize,
                    )
                }
            )
        }
    )
}