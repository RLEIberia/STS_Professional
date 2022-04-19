package com.rle.STS.ui.Screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rle.STS.BBDD.Database.STSDatabase
import com.rle.STS.R
import com.rle.STS.ui.theme.topBarColor
import com.rle.STS.ui.widgets.CustomButton
import kotlinx.coroutines.launch


@Composable
fun MainMenu(
    onProjectsClick: () -> Unit,
    onStateClick: () -> Unit,
) {

    //rememberSaveable for composition change
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()

    val context = LocalContext.current

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

        Column() {
            Spacer(modifier = Modifier.weight(1f))
            Row() {
                Spacer(modifier = Modifier.weight(1f))
                CustomButton(text = "Proyectos", onClick = { onProjectsClick() }, buttonSize = 220)
                Spacer(modifier = Modifier.weight(1f))
                CustomButton(text = "Videollamadas", onClick = {
                    val sendIntent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("msteams://teams.microsoft.com")
                    )
                    context.startActivity(sendIntent)
                }, buttonSize = 220)
                Spacer(modifier = Modifier.weight(1f))
                CustomButton(text = "Estado", onClick = { onStateClick() }, buttonSize = 220)
                Spacer(modifier = Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.weight(1f))
        }

    }

}