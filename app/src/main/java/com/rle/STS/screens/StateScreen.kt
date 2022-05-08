package com.rle.STS.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rle.STS.R
import com.rle.STS.ui.theme.topBarColor
import com.rle.STS.screens.widgets.CustomButton
import kotlinx.coroutines.launch

@Composable
fun StateScreen(

) {

    //rememberSaveable for composition change
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
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
        },
        drawerContent = {
            Drawer(scaffoldState = scaffoldState, scope = scope)
        },
    ) { contentPadding ->




    }

}
