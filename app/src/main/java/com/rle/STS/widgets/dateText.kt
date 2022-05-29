package com.rle.STS.widgets

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun dateText(
    date: SimpleDateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()),
    dateTimeStamp: Long,
    textColor: Color,
    style: TextStyle = MaterialTheme.typography.subtitle1
) {
    Text(
        text = date.format(dateTimeStamp * 1000).toString(),
        color = textColor,
        style = style
    )
}