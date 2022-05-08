package com.rle.STS.screens.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rle.STS.ui.theme.cardsColor

//Cards para mostrar los proyectos o checklists
@Composable
fun ListCardView(text: String, clickable: Boolean, onListClick: () -> Unit = {}) {

    Card(
        shape = RoundedCornerShape(8.dp),
        backgroundColor = cardsColor,
        elevation = 8.dp,
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth()
                .padding(start = 5.dp, end = 5.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (clickable) {
                Text(
                    text = text,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .semantics { this.contentDescription = text }
                        .clickable(clickable, onClick = onListClick),
                )
                //XMLTextView(text = text, onClick = { onListClick(text) })
            } else {
                Row() {
                    Column() {
                        Spacer(modifier = Modifier.weight(1f))
                        Text(text = text, fontSize = 25.sp)

                        Spacer(modifier = Modifier.weight(1f))

                        Text(text = text, fontSize = 15.sp)
                        Spacer(modifier = Modifier.weight(1f))
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Column() {
                        Spacer(modifier = Modifier.weight(1f))
                        Text(text = "Actualizado:", fontSize = 15.sp)
                        Spacer(modifier = Modifier.weight(1f))
                        Text(text = "23-03-2022", fontSize = 15.sp)
                        Spacer(modifier = Modifier.weight(1f))
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                }
            }
        }
    }
}