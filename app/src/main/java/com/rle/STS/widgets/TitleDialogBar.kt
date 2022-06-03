package com.rle.STS.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.rle.STS.ui.theme.topBarColor

@Composable
fun TitleDialogBar(title: String) {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth(),
        backgroundColor = topBarColor,
        elevation = 10.dp

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier
                    .padding(),
                fontWeight = FontWeight.Bold,
                text = title,
                style = MaterialTheme.typography.h4,
                color = Color.White
            )
        }
    }
}