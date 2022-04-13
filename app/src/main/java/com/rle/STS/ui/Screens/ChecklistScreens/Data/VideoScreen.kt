package com.rle.STS.ui.Screens.ChecklistScreens.Data

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import com.rle.STS.R
import com.rle.STS.ui.Screens.ChecklistScreens.CheckListStepViewModel
import com.rle.STS.ui.widgets.BottomButtons
import com.rle.STS.ui.widgets.CustomButton
import com.rle.STS.ui.widgets.VideoThumbnail
import java.io.File


@Composable
fun VideoScreen(file: String, stepViewModel: CheckListStepViewModel) {

    val context = LocalContext.current
    val videoPath: File = File(context.getFilesDir(), "Videos")
    val video = File(videoPath, file);

    Column {

        Spacer(modifier = Modifier.weight(1f))

        Row() {
            Spacer(modifier = Modifier.weight(1f))

            Card(
                modifier = Modifier
                    .width(450.dp)
                    .height(253.dp)
                    .clip(RoundedCornerShape(10))
                    .border(1.dp, Color.Black, RoundedCornerShape(10)),
            ) {

                VideoThumbnail(video = video)

            }

            Spacer(modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row() {

            Spacer(modifier = Modifier.weight(1f))

            CustomButton(
                text = stringResource(id = R.string.open_video),
                buttonSize = 180,
                onClick = {
                    val intent = Intent()
                    intent.setAction(Intent.ACTION_VIEW)
                    Log.d(
                        "FILE LOCATION:",
                        video.exists().toString()
                    ) // FileProvider.getUriForFile(context,context.packageName + ".provider", file).path!!)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        intent.setDataAndType(
                            FileProvider.getUriForFile(
                                context,
                                context.packageName + ".provider",
                                video
                            ), "video/*"
                        )
                    } else {
                        intent.setDataAndType(Uri.fromFile(video), "video/*")
                            .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    }
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    context.startActivity(intent)
                })

            Spacer(modifier = Modifier.weight(1f))

        }

        Spacer(modifier = Modifier.weight(1f))

        Spacer(modifier = Modifier.height(10.dp))

        val checkListPosition = stepViewModel.getPosition()
        val checkListSize = stepViewModel.getSize()

        BottomButtons(leftFunction = {
            if (checkListPosition.value!! > 0) {
                stepViewModel.setPosition(checkListPosition.value!! - 1)
            }
        }, rightFunction = {
            if (checkListPosition.value!! >= checkListSize.value!! - 1) {
                //Terminar checklist
            } else {
                stepViewModel.setPosition(checkListPosition.value!! + 1)
            }
        }) // Manejar botones desde aqui para cargar siguiente vista correctamente mediante metodo de lectura de JSON

        Spacer(modifier = Modifier.height(10.dp))

    }

}