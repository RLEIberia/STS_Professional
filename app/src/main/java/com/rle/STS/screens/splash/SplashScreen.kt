package com.rle.STS.screens.splash

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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rle.STS.ActivityViewModel
import com.rle.STS.R
import com.rle.STS.navigation.STSScreens
import com.rle.STS.repository.DbRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

@Composable
fun SplashScreen(
    navController: NavController,
    splashViewModel: SplashViewModel,
    activityViewModel: ActivityViewModel
) {

    val context = LocalContext.current

    LaunchedEffect(
        key1 = true,
        block = {
            delay(2000L)
            navController.navigate(STSScreens.MainScreen.name){
                popUpTo(0)
            }
        }
    )

    activityViewModel.createDirectories(context = context)
//    splashViewModel.sendResult(context = context, fileName = "simpleResult.json")

    Surface(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
            .padding(30.dp)
    ) {
        Image(painter = painterResource(id = R.drawable.sts_logo), contentDescription = "STS Logo")
    }

//    splashViewModel.navigate(
//        navController = navController
//    )

}