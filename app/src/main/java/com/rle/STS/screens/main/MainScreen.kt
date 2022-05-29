package com.rle.STS.screens

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.rle.STS.ActivityViewModel
import com.rle.STS.R
import com.rle.STS.navigation.STSScreens
import com.rle.STS.screens.main.MainViewModel
import com.rle.STS.screens.scanner.ScannerScreen
import com.rle.STS.screens.scanner.ScannerViewModel
import com.rle.STS.widgets.CustomTopIconButton
import com.rle.STS.widgets.SimpleTopBar


@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel,
    scannerViewModel: ScannerViewModel,
    activityViewModel: ActivityViewModel
) {

    //rememberSaveable for composition change
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()

    val toastAction = activityViewModel.toastAction.collectAsState()

    val context = LocalContext.current


    /* TODO - SACAR ESTO - PRUEBAS */

    LaunchedEffect(true) {
        Log.d("LAUNCH", "LaunchedEffect executed")
        mainViewModel.apiGetProjects(context = context)
    }

    if(toastAction.value) {
        Toast.makeText(context, "UserData not found, please, restart the device", Toast.LENGTH_LONG).show()
    }

    val openScanner = remember {
        mutableStateOf(false)
    }

    if(openScanner.value){
        ScannerScreen(
            navController = navController,
            openScanner = openScanner,
            mainViewModel = mainViewModel,
            scannerViewModel = scannerViewModel,
            activityViewModel = activityViewModel
        )
    }


    /* -------------------------- */

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            SimpleTopBar(
                scope,
                scaffoldState,
                text = "MENÚ PRINCIPAL",
            )
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
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CustomTopIconButton(
                        text = "Proyectos",
                        icon = R.drawable.pencil_icon,
                        onClick = {
                            navController.navigate(STSScreens.ProjectSelectScreen.name)
                        },
                        buttonColor = Color(0xffff8000),
                        buttonSize = 180
                    )
                    CustomTopIconButton(
                        text = "Llamada",
                        icon = R.drawable.call_icon,
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
                        icon = R.drawable.discover_icon,
                        onClick = {
                            /*TODO - Añadir dirección de estado*/
                            navController.navigate(STSScreens.ProjectSelectScreen.name)
                        },
                        buttonColor = Color(0xFFF44336),
                        buttonSize = 180
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CustomTopIconButton(
                        text = "Scanner",
                        icon = R.drawable.scan_qr,
                        onClick = {
                            openScanner.value = true
                        },
                        buttonColor = Color(0xFF9E1CAC),
                        buttonSize = 180
                    )
                    CustomTopIconButton(
                        text = "Documentos",
                        icon = R.drawable.call_icon,
                        onClick = {
                            navController.navigate(STSScreens.DocumentSelectScreen.name)
                        },
                        buttonColor = Color(0xFF533A36),
                        buttonSize = 180
                    )
                    CustomTopIconButton(
                        text = "Reporter",
                        icon = R.drawable.discover_icon,
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