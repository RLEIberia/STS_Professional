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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rle.STS.ActivityViewModel
import com.rle.STS.R
import com.rle.STS.model.DataStore.UserData
import com.rle.STS.navigation.STSScreens
import com.rle.STS.screens.main.MainViewModel
import com.rle.STS.ui.theme.topBarColor
import com.rle.STS.utils.converters.toChecklistsTable
import com.rle.STS.utils.converters.toProjectsTable
import com.rle.STS.widgets.CustomButton
import com.rle.STS.widgets.CustomTopIconButton
import com.rle.STS.widgets.SimpleTopBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel,
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



//    val test = mainViewModel.APIprojectResponse.collectAsState().value //TODO QUITAR COMO TEST

//    if (test.data != null) {
//
//        mainViewModel.insertMultipleProjects(test)
//        mainViewModel.insertMultipleChecklists(test)
//        //mainViewModel.insertProject(testProjects[0])
//        //mainViewModel.insertChecklist(testChecklists[0])
//
//    }



//    mainViewModel.getMultipleProjectsByIds(arrayOf(1,2))
//    val multiP = mainViewModel.multipleProjectsByIds.collectAsState().value
//    if(multiP.isNotEmpty()){
//        Log.d("MULTIP", multiP.toString())
//    }




    /* -------------------------- */

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            SimpleTopBar(scope, scaffoldState)
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

            }

        }

    }

}