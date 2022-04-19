package com.rle.STS.ui.Screens.ChecklistScreens.Repository

import androidx.compose.runtime.Composable
import com.rle.STS.ui.Screens.ChecklistScreens.CheckListStepViewModel
import com.rle.STS.ui.Screens.ChecklistScreens.Data.ImageScreen

class Repository {

    companion object {

        @Composable
        fun Im0(file: String, viewmodel: CheckListStepViewModel){
            ImageScreen(file = file, type = 0, stepViewModel = viewmodel)
        }

        @Composable
        fun Im1(file: String, viewmodel: CheckListStepViewModel){
            ImageScreen(file = file, type = 1, stepViewModel = viewmodel)
        }

        @Composable
        fun Im2(file: String, viewmodel: CheckListStepViewModel){
            ImageScreen(file = file, type = 2, stepViewModel = viewmodel)
        }




    }

}