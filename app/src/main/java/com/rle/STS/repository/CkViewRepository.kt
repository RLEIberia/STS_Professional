package com.rle.STS.screens

import androidx.compose.runtime.Composable
import com.rle.STS.viewScreens.attach.*
import com.rle.STS.screens.checklist.ChecklistViewModel
import com.rle.STS.viewScreens.data.AudioScreen
import com.rle.STS.viewScreens.data.ImageScreen
import com.rle.STS.viewScreens.data.QRScreen
import com.rle.STS.viewScreens.data.VideoScreen
import com.rle.STS.viewScreens.result.MultiOptionScreen
import com.rle.STS.viewScreens.result.OKKOScreen

class CkViewRepository {

    companion object {


        //Imágenes
        @Composable
        fun IM1(file: String, viewModel: ChecklistViewModel) { ImageScreen(file = file, type = 0, stepViewModel = viewModel) }
        @Composable
        fun IM2(file: String, viewModel: ChecklistViewModel) { ImageScreen(file = file, type = 1, stepViewModel = viewModel) }
        @Composable
        fun IM3(file: String, viewModel: ChecklistViewModel) { ImageScreen(file = file, type = 2, stepViewModel = viewModel) }

        //Vídeo
        @Composable
        fun VD1(file: String, viewModel: ChecklistViewModel) { VideoScreen(file = "The RealWear HMT-1.mp4", stepViewModel = viewModel) }

        //QR
        @Composable
        fun QR1(viewModel: ChecklistViewModel, nextType: Int) { QRScreen(type = 0, { true }, stepViewModel = viewModel, nextType) }
        @Composable
        fun QR2(viewModel: ChecklistViewModel, nextType: Int) { QRScreen(type = 1, { true }, stepViewModel = viewModel, nextType) }
        @Composable
        fun QR3(viewModel: ChecklistViewModel, nextType: Int) { QRScreen(type = 2, { true }, stepViewModel = viewModel, nextType) }

        //Audio
        @Composable
        fun AU1(viewModel: ChecklistViewModel, nextType: Int) { DictateScreen(viewModel, nextType) }
        @Composable
        fun AU2(file: String, viewModel: ChecklistViewModel) { AudioScreen(file = "Grabacion.m4a", viewModel) }
        @Composable
        fun AU3(viewModel: ChecklistViewModel, nextType: Int) { RecordAudioScreen(viewModel, nextType) }

        //Cámara
        @Composable
        fun CM1(viewModel: ChecklistViewModel, nextType: Int) { TakePictureScreen(viewModel, nextType) }
        @Composable
        fun CM2(viewModel: ChecklistViewModel, nextType: Int) { TakeVideoScreen(viewModel, nextType) }


        @Composable
        fun NumberScreen1(check: () -> Unit, viewModel: ChecklistViewModel, nextType: Int) {
            NumberScreen(check, viewModel, nextType)
        }


        @Composable
        fun OP1(viewModel: ChecklistViewModel, nextType: Int) {
            OKKOScreen(viewModel, nextType)
        }


        @Composable
        fun MultiOption2(viewModel: ChecklistViewModel, nextType: Int) {
            MultiOptionScreen(
                option1 = "Tomar el camino de la derecha",
                option2 = "Tomar el camino de la izquierda",
                option3 = "Tomar el camino central",
                stepViewModel = viewModel,
                nextType = 0
            )
        }

        @Composable
        fun MultiOption3(viewModel: ChecklistViewModel, nextType: Int) {
            MultiOptionScreen(
                option1 = "option1",
                option2 = "option2",
                option3 = "option3",
                stepViewModel = viewModel,
                nextType = 0
            )
        }

        @Composable
        fun MultiOption4(viewModel: ChecklistViewModel, nextType: Int) {
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