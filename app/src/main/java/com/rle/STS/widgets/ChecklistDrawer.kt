package com.rle.STS.screens


import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rle.STS.MainActivity
import com.rle.STS.R
import com.rle.STS.items.NavDrawerItem
import com.rle.STS.items.NavDrawerItemChecklist
import com.rle.STS.navigation.STSScreens
import com.rle.STS.screens.checklist.ChecklistViewModel
import com.rle.STS.ui.theme.buttonOkColor
import com.rle.STS.ui.theme.grayedButton
import com.rle.STS.ui.theme.topBarColor
import com.rle.STS.widgets.CustomSideIconButton
import com.rle.STS.widgets.CustomText
import com.rle.STS.widgets.XMLText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ChecklistDrawer(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    navController: NavController,
    checklistViewModel: ChecklistViewModel
) {
    val items = listOf(
        NavDrawerItemChecklist.Paso_anterior,
        NavDrawerItemChecklist.Paso_siguiente,
        NavDrawerItemChecklist.Volver,
        NavDrawerItemChecklist.Inicio
    )

    val ttsOn = checklistViewModel.ttsOn.collectAsState()

    //TODO Drawer customizable desde parámetros

    Column(
        modifier = Modifier
            .background(Color(0xFF206f5c))
            .padding(top = 15.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
        ) {
            Spacer(modifier = Modifier.width(25.dp))
            Card(
                modifier = Modifier
                    .padding(5.dp)
                    .border(width = 2.dp, color = Color.Black, shape = RoundedCornerShape(10)),
                shape = RoundedCornerShape(10),
                elevation = 4.dp,
                backgroundColor = Color.White
            ) {
                Image(
                    painter = painterResource(id = R.mipmap.rle_logo),
                    contentDescription = R.drawable.ic_launcher_foreground.toString(),
                    modifier = Modifier
                        .size(150.dp)
                        .padding(10.dp)
                )
            }


            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                //Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "RLE International Iberia",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h4,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                //Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "José Cabello Orts",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h5,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            CustomSideIconButton(
                text = when (ttsOn.value) {
                    true -> "DESACTIVAR AUDIO"
                    false -> "ACTIVAR AUDIO"
                },
                onClick = { checklistViewModel.ttsSwitch()},
                icon = R.drawable.speaker_icon,
                buttonSize = 350,
                buttonColor = when (ttsOn.value) {
                    true -> buttonOkColor
                    false -> grayedButton
                }
            )
        }


        Spacer(modifier = Modifier.height(20.dp))

        // Header
        /*Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = R.drawable.logo.toString(),
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .padding(10.dp)
        )
        // Space between
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(5.dp)
        )*/
        items.forEach { item ->


            Row() {
                Spacer(modifier = Modifier.weight(1f))
                XMLText(text = item.title, onClick = {
                    if (item == NavDrawerItemChecklist.Inicio) {
                        navController.navigate(STSScreens.MainScreen.name)
                    } else if (item == NavDrawerItemChecklist.Paso_siguiente) {

                    } else if (item == NavDrawerItemChecklist.Paso_anterior) {

                    } else if (item == NavDrawerItemChecklist.Volver) {
                        scope.launch {
                            scaffoldState.drawerState.close()
                        }
                    }

                }, textColorParameter = Color.White, enabled = true)
                Spacer(modifier = Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.height(5.dp))


        }
        Spacer(modifier = Modifier.weight(1f))
    }
}

//@Preview(showBackground = true)
//@Composable
//fun DrawerPreview() {
//    val scope = rememberCoroutineScope()
//    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
//    val navController = rememberNavController()
//    Drawer(scope = scope, scaffoldState = scaffoldState)
//}