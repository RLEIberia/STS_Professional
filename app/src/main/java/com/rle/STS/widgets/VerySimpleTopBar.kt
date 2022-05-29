package com.rle.STS.widgets

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rle.STS.R
import com.rle.STS.ui.theme.topBarColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

//TODO - Estandarizar SimpleTopBar

@Composable
fun VerySimpleTopBar(
    text: String = "",
    leftExists: Boolean = false,
    leftVisible: Boolean = false,
    leftOnClick: () -> Unit = {},
    leftText: String = "LEFT",
    rightExist: Boolean = false,
    rightVisible: Boolean = false,
    rightOnClick: () -> Unit = {},
    rightText: String = "RIGHT",
) {
    TopAppBar(
        backgroundColor = topBarColor,
        modifier = Modifier.height(60.dp),

        ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Spacer(modifier = Modifier.width(10.dp))
                if(leftExists) {
                    Column(
                        modifier = Modifier
                            .weight(0.2f)
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Center
                    ) {
                        if (leftVisible) {
                            CustomButton(
                                text = leftText,
                                onClick = leftOnClick
                            )
                        }

                    }
                }
                Column(
                    modifier = Modifier
                        .weight(0.5f)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = text,
                        fontSize = 25.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold

                    )
                }
                if(rightExist) {
                    Column(
                        modifier = Modifier
                            .weight(0.2f)
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.End,
                        verticalArrangement = Arrangement.Center
                    ) {
                        if (rightVisible) {
                            CustomButton(
                                text = rightText,
                                onClick = leftOnClick
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.width(10.dp))

            }
        }
    }
}