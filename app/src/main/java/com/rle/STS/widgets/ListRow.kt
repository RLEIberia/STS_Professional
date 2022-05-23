package com.rle.STS.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ListRow(
    buttonLetter: String = "X",
    height: Dp = 80.dp,
    buttonColor: Color = Color.Gray,
    rowColor: Color = Color.LightGray,
    textColor: Color = Color.Black,
    number: Int = 0,
    onClick: () -> Unit = {},
    title: String = "Title",
    dateTimeStamp: Long? = null
) {

    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .height(height),
        elevation = 10.dp,
        shape = RoundedCornerShape(corner = CornerSize(10.dp))
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            ListButton(
                buttonLetter = buttonLetter,
                number = number,
                onClick = onClick,
                buttonColor = buttonColor
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(rowColor)
                    .padding(bottom = 5.dp),
                verticalArrangement = Arrangement.Center
            ) {
                TitleRow(
                    title = title,
                    textColor = textColor
                )
                if (dateTimeStamp != null)
                    DateRow(
                        dateTimeStamp = dateTimeStamp,
                        textColor = textColor
                    )
            }

        }
    }
}

@Composable
private fun TitleRow(
    title: String,
    textColor: Color,
) {
    Row(
        modifier = Modifier
            .padding(end = 7.dp, start = 7.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.h5,
            color = textColor,
            fontWeight = FontWeight.Bold,
            maxLines = 1
        )
        Spacer(modifier = Modifier.padding(5.dp))
    }
}

@Composable
private fun DateRow(
    dateTimeStamp: Long,
    textColor: Color,
) {
    val date = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    Row(
        modifier = Modifier
            .padding(end = 7.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Text(
            text = "Updated: ", //Cambiar a texto dependiendo del idioma
            style = MaterialTheme.typography.subtitle1,
            color = textColor,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = date.format(dateTimeStamp * 1000).toString(),
            color = textColor,
            style = MaterialTheme.typography.subtitle1,
        )
    }
}

@Composable
private fun ListButton(
    buttonLetter: String,
    number: Int,
    buttonColor: Color = Color.Gray,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth(0.15f)
            .fillMaxHeight(),
        elevation = 500.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(buttonColor),
            Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            XMLText(text = "$buttonLetter${number+1}", onClick = { onClick() }, enabled = true)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ListRowPreview() {
    ListRow()
}