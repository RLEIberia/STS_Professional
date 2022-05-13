package com.rle.STS.screens.splash

import android.util.Log
import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rle.STS.R
import com.rle.STS.logic.json.extractChecklist
import com.rle.STS.navigation.STSScreens
import com.rle.STS.utils.GetJsonDataFromAsset
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController
) {

    LaunchedEffect(
        key1 = true,
        block = {
            delay(2000L)
            navController.navigate(STSScreens.MainScreen.name){
                popUpTo(0)
            }
        }
    )


    Surface(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
            .padding(30.dp)
    ) {
        Image(painter = painterResource(id = R.drawable.sts_logo), contentDescription = "STS Logo")
    }
}