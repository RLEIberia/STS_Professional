package com.rle.STS.Screens.ChecklistScreens.Repository

import androidx.compose.runtime.Composable
import com.rle.STS.Screens.ChecklistScreens.Attached.*
import com.rle.STS.Screens.ChecklistScreens.CheckListStepViewModel
import com.rle.STS.Screens.ChecklistScreens.Data.AudioScreen
import com.rle.STS.Screens.ChecklistScreens.Data.ImageScreen
import com.rle.STS.Screens.ChecklistScreens.Data.QRScreen
import com.rle.STS.Screens.ChecklistScreens.Data.VideoScreen
import com.rle.STS.Screens.ChecklistScreens.Result.MultiOptionScreen
import com.rle.STS.Screens.ChecklistScreens.Result.OKKOScreen

class Repository {

    companion object {

        @Composable
        fun Im0(file: String, viewModel: CheckListStepViewModel){
            ImageScreen(file = file, type = 0, stepViewModel = viewModel)
        }

        @Composable
        fun Im1(file: String, viewModel: CheckListStepViewModel){
            ImageScreen(file = file, type = 1, stepViewModel = viewModel)
        }

        @Composable
        fun Im2(file: String, viewModel: CheckListStepViewModel){
            ImageScreen(file = file, type = 2, stepViewModel = viewModel)
        }

        @Composable
        fun QR0(viewModel: CheckListStepViewModel, nextType: Int){
            QRScreen(type = 0, {true}, stepViewModel = viewModel, nextType)
        }

        @Composable
        fun QR1(viewModel: CheckListStepViewModel, nextType: Int){
            QRScreen(type = 1, {true}, stepViewModel = viewModel, nextType)
        }

        @Composable
        fun QR2(viewModel: CheckListStepViewModel, nextType: Int){
            QRScreen(type = 2, {true}, stepViewModel = viewModel, nextType)
        }

        @Composable
        fun VideoScreen1(file : String, viewModel: CheckListStepViewModel){
            VideoScreen(file = "test.mp4", viewModel)
        }

        @Composable
        fun AudioScreen1(file : String, viewModel: CheckListStepViewModel){
            AudioScreen(file = "Grabacion.m4a", viewModel)
        }

        @Composable
        fun RecordAudioScreen1(viewModel: CheckListStepViewModel, nextType: Int){
            RecordAudioScreen(viewModel, nextType)
        }

        @Composable
        fun DictateScreen1(viewModel: CheckListStepViewModel, nextType: Int){
            DictateScreen(viewModel, nextType)
        }

        @Composable
        fun TakePictureScreen1(viewModel: CheckListStepViewModel, nextType: Int){
            TakePictureScreen(viewModel, nextType)
        }

        @Composable
        fun TakeVideoScreen1(viewModel: CheckListStepViewModel, nextType: Int){
            TakeVideoScreen(viewModel, nextType)
        }


        @Composable
        fun NumberScreen1(check : () -> Unit, viewModel: CheckListStepViewModel, nextType: Int){
            NumberScreen(check, viewModel, nextType)
        }


        @Composable
        fun OKKOScreen1(viewModel: CheckListStepViewModel, nextType: Int){
            OKKOScreen(viewModel, nextType)
        }


        @Composable
        fun MultiOption2(viewModel: CheckListStepViewModel, nextType: Int){
            MultiOptionScreen(
                option1 = "option1",
                option2 = "option2",
                stepViewModel = viewModel,
                nextType = 0
            )
        }

        @Composable
        fun MultiOption3(viewModel: CheckListStepViewModel, nextType: Int){
            MultiOptionScreen(
                option1 = "option1",
                option2 = "option2",
                option3 = "option3",
                stepViewModel = viewModel,
                nextType = 0
            )
        }

        @Composable
        fun MultiOption4(viewModel: CheckListStepViewModel, nextType: Int){
            MultiOptionScreen(
                option1 = "option1",
                option2 = "option2",
                option3 = "option3",
                option4 = "option4",
                stepViewModel = viewModel,
                nextType = 0
            )
        }

    }

}