package com.rle.STS.ui.Screens.ChecklistScreens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rle.STS.R
import com.rle.STS.ui.Screens.ChecklistScreens.Attached.*
import com.rle.STS.ui.Screens.ChecklistScreens.Data.*
import com.rle.STS.ui.theme.CheckListaApplicationTheme
import com.rle.STS.ui.theme.topBarColor
import com.rle.STS.ui.widgets.BottomButtons
import com.rle.STS.ui.widgets.CustomButton
import kotlinx.coroutines.launch

@Composable
fun CheckListStepScreen() {

    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()

    Scaffold(
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
                        CustomButton(text = stringResource(R.string.audio), onClick = {
                            scope.launch {
                                scaffoldState.drawerState.open()
                            }
                        })
                        Spacer(modifier = Modifier.width(10.dp))
                    }
                    Spacer(modifier = Modifier.weight(1f))
                }

            }
        },

    ) { // Cargar JSON y seleccionar vista actual, Crear metodo que lea JSON y devuelva siguiente vista

        Column {

            Spacer(modifier = Modifier.weight(1f))

            // DATA SCREENS

            //TextScreen()
            //ImageScreen(file = "Capture.PNG")
            //VideoScreen(file = "test.mp4")
            //AudioScreen()


            // MULTI-USE SCREENS

            //QRScreen()


            // ATTACHED SCREENS

            //RecordAudioScreen()
            //DictateScreen()
            TakePictureScreen()   //Lo guardan en un sitio no memoria interna
            //TakeVideoScreen()     //Lo guardan en un sitio no memoria interna
            //NumberScreen()          //En proceso

            /* TODO
                Finalizar pantallas en proceso
             */

            // RESULT SCREENS

            /* TODO
                CheckNumberScreen()
                MultiOptionQRI()
                MultiOptionScreen()
                OKKOScreen()
            */

            Spacer(modifier = Modifier.height(10.dp))

            BottomButtons() // Manejar botones desde aqui para cargar siguiente vista correctamente mediante metodo de lectura de JSON

            Spacer(modifier = Modifier.height(10.dp))

        }

    }


}


@Preview(showBackground = true, widthDp = 851, heightDp = 480)
@Composable
private fun DefaultPreview() {
    CheckListaApplicationTheme {
        CheckListStepScreen()
    }
}