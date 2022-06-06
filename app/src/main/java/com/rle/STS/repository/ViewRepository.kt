package com.rle.STS.screens

import androidx.compose.runtime.Composable
import com.rle.STS.screens.viewScreens.attach.*
import com.rle.STS.viewModel.ChecklistViewModel
import com.rle.STS.screens.viewScreens.data.*
import com.rle.STS.screens.viewScreens.result.OKKOScreen
import com.rle.STS.screens.viewScreens.result.OP2Screen
import javax.inject.Inject

class ViewRepository @Inject constructor(){

    companion object {


        //Imágenes
        @Composable
        fun IM1(viewModel: ChecklistViewModel) { ImageScreen(type = 0, checklistViewModel = viewModel) }
        @Composable
        fun IM2(viewModel: ChecklistViewModel) { ImageScreen(type = 1, checklistViewModel = viewModel) }
        @Composable
        fun IM3(viewModel: ChecklistViewModel) { ImageScreen(type = 2, checklistViewModel = viewModel) }

        //Vídeo
        @Composable
        fun VD1(checklistViewModel: ChecklistViewModel) { VideoScreen(checklistViewModel = checklistViewModel) }

        //QR
        @Composable
        fun QR1(checklistViewModel: ChecklistViewModel) {
            QR1Screen(checklistViewModel = checklistViewModel)
        }
        @Composable
        fun QR2(checklistViewModel: ChecklistViewModel, nextType: Int) { QRScreen(type = 1, { true }, checklistViewModel = checklistViewModel) }
        @Composable
        fun QR3(checklistViewModel: ChecklistViewModel, nextType: Int) { QRScreen(type = 2, { true }, checklistViewModel = checklistViewModel) }

        //Audio
        @Composable
        fun AU1(checklistViewModel: ChecklistViewModel) { AU1Screen(checklistViewModel = checklistViewModel) }
        @Composable
        fun AU2(checklistViewModel: ChecklistViewModel) { AU2Screen(checklistViewModel = checklistViewModel) }
        @Composable
        fun AU3(viewModel: ChecklistViewModel, nextType: Int) { RecordAudioScreen(viewModel, nextType) }

        //Cámara
        @Composable
        fun CM1(checklistViewModel: ChecklistViewModel) { TakePictureScreen(checklistViewModel) }
        @Composable
        fun CM2(checklistViewModel: ChecklistViewModel) { TakeVideoScreen(checklistViewModel) }


        @Composable
        fun NM1(checklistViewModel: ChecklistViewModel){
            NM1Screen(checklistViewModel = checklistViewModel)
        }

        //Text
        @Composable
        fun TX1(checklistViewModel: ChecklistViewModel){
            Text1(checklistViewModel = checklistViewModel)
        }
        @Composable
        fun TX2(checklistViewModel: ChecklistViewModel){
            Text2(checklistViewModel = checklistViewModel)
        }
        @Composable
        fun TX3(checklistViewModel: ChecklistViewModel){
            Text3(checklistViewModel = checklistViewModel)
        }

        //Options
        @Composable
        fun OP1(checklistViewModel: ChecklistViewModel) {
            OKKOScreen(checklistViewModel)
        }


        @Composable
        fun OP2(checklistViewModel: ChecklistViewModel){
            OP2Screen(checklistViewModel = checklistViewModel)
        }
        
        @Composable
        fun PDF1(checklistViewModel: ChecklistViewModel){
            PDFScreen(checklistViewModel = checklistViewModel)
        }

    }

}