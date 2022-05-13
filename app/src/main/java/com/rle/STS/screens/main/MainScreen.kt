package com.rle.STS.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rle.STS.R
import com.rle.STS.navigation.STSScreens
import com.rle.STS.screens.main.MainViewModel
import com.rle.STS.ui.theme.topBarColor
import com.rle.STS.widgets.CustomButton
import com.rle.STS.widgets.CustomTopIconButton
import kotlinx.coroutines.launch


@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel()
) {

    //rememberSaveable for composition change
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()


    val context = LocalContext.current


    /* TODO - SACAR ESTO - PRUEBAS */
    val checklistData = mainViewModel.checklistData.collectAsState().value
    mainViewModel.extractChecklist(context = context, fileName = "exampleChecklist.json")
    Log.d("CK", checklistData.toString())
    /* -------------------------- */

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

        Surface(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CustomTopIconButton(
                        text = "Proyectos",
                        icon = R.drawable.correct_icon, //pencil_icon
                        onClick = {
                            navController.navigate(STSScreens.ProjectSelectScreen.name)
                        },
                        buttonColor = Color(0xffff8000),
                        buttonSize = 180
                    )
                    CustomTopIconButton(
                        text = "Llamada",
                        icon = R.drawable.correct_icon, //call_icon
                        onClick = {
                            val sendIntent = Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("msteams://teams.microsoft.com")
                            )
                            context.startActivity(sendIntent)
                        },
                        buttonColor = Color(0xFF366CF4),
                        buttonSize = 180
                    )
                    CustomTopIconButton(
                        text = "Estado",
                        icon = R.drawable.correct_icon, //discover_icon
                        onClick = {
                            /*TODO - Añadir dirección de estado*/
                            navController.navigate(STSScreens.ProjectSelectScreen.name)
                        },
                        buttonColor = Color(0xFFF44336),
                        buttonSize = 180
                    )
                }

            }

        }

    }

}