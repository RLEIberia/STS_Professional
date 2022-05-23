package com.rle.STS.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.google.android.material.bottomappbar.BottomAppBar
import com.rle.STS.ui.theme.buttonsColor
import com.rle.STS.R
import com.rle.STS.ui.theme.backgroundColor
import com.rle.STS.ui.theme.bottomBarColor

@Composable
fun BottomBar(
    modifierBottomBar: Modifier,
    leftActive: Boolean = true,
    leftText: String = "LEFT",
    leftIcon: Int = R.drawable.correct_icon,
    leftOnClick: () -> Unit,
    leftColor: Color = buttonsColor,
    leftSize: Int = 150,
    centerActive: Boolean = false,
    centerText: String = "CENTER",
    centerIcon: Int = R.drawable.correct_icon,
    centerOnClick: () -> Unit,
    centerColor: Color = buttonsColor,
    centerSize: Int = 150,
    rightActive: Boolean = true,
    rightText: String = "RIGHT",
    rightIcon: Int = R.drawable.correct_icon,
    rightOnClick: () -> Unit,
    rightColor: Color = buttonsColor,
    rightSize: Int = 150
) {


//        Column(
//            modifier = Modifier
//                .background(Color.Gray)
//                .fillMaxWidth()
//                .height(20.dp),
//        ){}
    BottomAppBar(
        modifier = modifierBottomBar,
        backgroundColor = bottomBarColor,
        elevation = 10.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .weight(1f)
            ) {
                if (leftActive) {
                    CustomSideIconButton(
                        text = leftText,
                        buttonColor = leftColor,
                        icon = leftIcon!!,
                        buttonSize = leftSize,
                        onClick = { leftOnClick() },
                    )
                }
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(1f)
            ) {
                if (centerActive) {
                    CustomSideIconButton(
                        text = centerText,
                        buttonColor = centerColor,
                        icon = centerIcon,
                        buttonSize = centerSize,
                        onClick = { centerOnClick() }
                    )
                }
            }

            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier
                    .weight(1f)
            ) {
                if (rightActive) {
                    CustomSideIconButton(
                        text = rightText,
                        buttonColor = rightColor,
                        icon = rightIcon,
                        buttonSize = rightSize,
                        onClick = { rightOnClick() }
                    )
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun BottomBarPreview() {
//    BottomBar(centerActive = false)
//}