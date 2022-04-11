package com.rle.STS.ui.Screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.rle.STS.R
import com.rle.STS.ui.Items.NavDrawerItem
import com.rle.STS.ui.theme.topBarColor
import com.rle.STS.ui.widgets.CustomText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun Drawer(scope: CoroutineScope, scaffoldState: ScaffoldState) {
    val items = listOf(
        NavDrawerItem.Home,
        NavDrawerItem.Music,
        NavDrawerItem.Movies,
        NavDrawerItem.Books,
        NavDrawerItem.Profile,
        NavDrawerItem.Settings
    )
    Column(
        modifier = Modifier.background(topBarColor)
    ) {

        Row(Modifier.height(80.dp)) {
            Spacer(modifier = Modifier.width(25.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = R.drawable.ic_launcher_foreground.toString(),
                modifier = Modifier
                    .height(80.dp)
                    .width(80.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            Column( verticalArrangement = Arrangement.Center, ) {
                //Spacer(modifier = Modifier.height(10.dp))
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "EMPRESA", textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.weight(1f))
                //Spacer(modifier = Modifier.height(10.dp))
                Text(text = "DATOS LOGIN", textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.weight(1f))
                //Spacer(modifier = Modifier.height(10.dp))
            }
            Spacer(modifier = Modifier.weight(1f))

        }
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
                CustomText(text = item.title, onClick = {
                    scope.launch {
                        scaffoldState.drawerState.close()
                    }
                })
                Spacer(modifier = Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.height(5.dp))


        }
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Preview(showBackground = true)
@Composable
fun DrawerPreview() {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val navController = rememberNavController()
    Drawer(scope = scope, scaffoldState = scaffoldState)
}