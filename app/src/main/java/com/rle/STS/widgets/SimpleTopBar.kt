package com.rle.STS.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.material.ScaffoldState
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rle.STS.R
import com.rle.STS.ui.theme.topBarColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

//TODO - Estandarizar SimpleTopBar

@Composable
fun SimpleTopBar(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState
) {
    TopAppBar(
        backgroundColor = topBarColor,
        modifier = Modifier.height(60.dp)
    ) {
        Column() {
            Spacer(modifier = Modifier.weight(1f))
            Row() {
                Spacer(modifier = Modifier.width(10.dp))
                CustomButton(text = stringResource(R.string.menu), onClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                })
                Spacer(modifier = Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.weight(1f))
        }

    }
}