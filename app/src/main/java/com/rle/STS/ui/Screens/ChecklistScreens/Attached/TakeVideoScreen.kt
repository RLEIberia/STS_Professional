package com.rle.STS.ui.Screens.ChecklistScreens.Attached

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.core.content.FileProvider
import com.rle.STS.R
import com.rle.STS.ui.Screens.ChecklistScreens.CheckListStepViewModel
import com.rle.STS.ui.theme.CheckListaApplicationTheme
import com.rle.STS.ui.theme.buttonExtraColor
import com.rle.STS.ui.theme.cardsColor
import com.rle.STS.ui.widgets.BottomButtons
import com.rle.STS.ui.widgets.CustomButton
import com.rle.STS.ui.widgets.VideoThumbnail
import com.rle.STS.ui.widgets.defaultStepBottomButtons
import java.io.File

@SuppressLint("RestrictedApi")
@Composable
fun TakeVideoScreen(stepViewModel: CheckListStepViewModel) {

    val context = LocalContext.current

    val project = "1"
    val checklist = "1"
    val user = "user"

    val directoryS = context.filesDir.path + File.separator + "Images" + File.separator + "Taken"

    val directory = File(directoryS)

    lateinit var file: File

    for (video in directory.listFiles()!!) {
        if (video.length() == 0L) {
            video.delete()
        }
    }

    file = createVideoFile(context, project, checklist, user)

    val uri = FileProvider.getUriForFile(context, context.packageName + ".provider", file)
    val uriSegments = uri.pathSegments
    var uriString = context.filesDir.path
    for (i in 1 until uriSegments.size) {
        uriString = uriString + "/" + uriSegments[i]
    }
    val fileList = remember { mutableStateListOf<File>() }
    val openDialog = remember { mutableStateOf(false) }
    val openConfirmDialog = remember { mutableStateOf(false) }
    val openVideo = remember { mutableStateOf(File("")) }
    val launcherVideo =
        rememberLauncherForActivityResult(ActivityResultContracts.CaptureVideo()) {
            if (it) {
                openVideo.value = File(uriString)

                openConfirmDialog.value = true
            }
        }

    Column() {

        Spacer(modifier = Modifier.weight(1f))

        Row() {

            Spacer(modifier = Modifier.weight(1f))

            Column() {

                Row() {

                    fileList.let { list ->

                        Card(
                            modifier = Modifier
                                .width(450.dp)
                                .height(253.dp)
                                .clip(RoundedCornerShape(10))
                                .border(1.dp, Color.Black, RoundedCornerShape(10)),
                        ) {

                            if (list.isNotEmpty()) {

                                VideoThumbnail(video = list.last())

                            } else {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(Color.Gray),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_baseline_image_24),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .width(250.dp)
                                            .height(140.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Column(
                modifier = Modifier.height(260.dp)
            ) {

                Spacer(modifier = Modifier.weight(1f))

                CustomButton(
                    text = stringResource(id = R.string.open_list),
                    onClick = { if (fileList.size > 0) openDialog.value = true },
                    buttonSize = 150
                )

                Spacer(modifier = Modifier.weight(1f))

                Card(
                    shape = RoundedCornerShape(8.dp),
                    backgroundColor = cardsColor,
                    elevation = 0.dp,
                    modifier = Modifier
                        .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp)
                        .width(150.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .height(80.dp)
                            .fillMaxWidth()
                            .padding(start = 5.dp, end = 5.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(text = stringResource(id = R.string.total_videos))
                        Text(text = fileList.size.toString())
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

            }

            Spacer(modifier = Modifier.weight(1f))

        }

        Spacer(modifier = Modifier.height(10.dp))

        Row() {
            Spacer(modifier = Modifier.weight(1f))

            CustomButton(
                text = stringResource(R.string.record_video),
                onClick = { launcherVideo.launch(uri) },
                buttonSize = 200,
                buttonColor = buttonExtraColor,
            )

            Spacer(modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.weight(1f))

        Spacer(modifier = Modifier.height(10.dp))

        val checkListPosition = stepViewModel.checkListPosition.collectAsState()
        val checkListSize = stepViewModel.checkListSize.collectAsState()

        // TODO: Comprobar que haya grabado algo
        defaultStepBottomButtons(stepViewModel)

        Spacer(modifier = Modifier.height(10.dp))

    }

    if (openConfirmDialog.value) {
        ConfirmVideoDialog(
            file = openVideo.value,
            fileList = fileList,
            openConfirmDialog = openConfirmDialog,
            context = context
        )
    }

    if (openDialog.value) {
        videosListDialog(
            openDialog = openDialog,
            fileList = fileList,
            context = context
        )
    }

}





@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ConfirmVideoDialog(
    file: File,
    fileList: SnapshotStateList<File>,
    openConfirmDialog: MutableState<Boolean>,
    context: Context
) {

    AlertDialog(
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),
        modifier = Modifier
            .width(700.dp)
            .height(450.dp),
        onDismissRequest = {
            openConfirmDialog.value = false
        },
        text = {
            Row() {
                Spacer(modifier = Modifier.weight(1f))
                Column() {
                    Spacer(modifier = Modifier.height(20.dp))

                    Card(
                        modifier = Modifier
                            .width(450.dp)
                            .height(253.dp)
                            .clip(RoundedCornerShape(10))
                            .border(1.dp, Color.Black, RoundedCornerShape(10)),
                    ) {

                        VideoThumbnail(video = file)

                    }

                    Spacer(modifier = Modifier.height(20.dp))
                }
                Spacer(modifier = Modifier.weight(1f))
            }
        },
        buttons = {
            Row(
                modifier = Modifier.padding(all = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                BottomButtons(
                    leftText = stringResource(id = R.string.cancel),
                    leftFunction = {
                        file.delete()
                        openConfirmDialog.value = false
                    },
                    rightText = stringResource(id = R.string.confirm),
                    rightFunction = {
                        fileList.add(file)
                        openConfirmDialog.value = false
                    },
                    middleText = stringResource(id = R.string.inspect_s),
                    middleFunction = {
                        val intent = Intent()
                        intent.setAction(Intent.ACTION_VIEW)
                        intent.setDataAndType(
                            FileProvider.getUriForFile(
                                context,
                                context.packageName + ".provider",
                                file
                            ), "image/*"
                        )
                        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                        context.startActivity(intent)
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    )
}



@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun videosListDialog(
    openDialog: MutableState<Boolean>,
    fileList: SnapshotStateList<File>,
    context: Context
) {
    val video = remember { mutableStateOf(0) }
    val openDialog2 = remember { mutableStateOf(false) }

    AlertDialog(
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),
        modifier = Modifier
            .width(700.dp)
            .height(450.dp),
        onDismissRequest = {
            openDialog.value = false
        },
        text = {
            Row() {
                Spacer(modifier = Modifier.weight(1f))

                Column() {
                    Spacer(modifier = Modifier.height(20.dp))
                    CustomButton(
                        text = stringResource(id = R.string.select),
                        buttonSize = 480,
                        onClick = {
                            openDialog2.value = true
                        }
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    if (fileList.size > video.value) {
                        Card(
                            modifier = Modifier
                                .width(450.dp)
                                .height(253.dp)
                                .clip(RoundedCornerShape(10))
                                .border(1.dp, Color.Black, RoundedCornerShape(10)),
                        ) {

                            VideoThumbnail(fileList[video.value])

                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                }
                Spacer(modifier = Modifier.weight(1f))
            }

        },
        buttons = {
            Row(
                modifier = Modifier.padding(all = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                BottomButtons(
                    leftVisible = video.value > 0,
                    leftText = stringResource(id = R.string.previous),
                    leftFunction = { video.value-- },
                    rightVisible = video.value < fileList.size - 1,
                    rightText = stringResource(id = R.string.next),
                    rightFunction = { video.value++ },
                    middleText = stringResource(id = R.string.go_back),
                    middleFunction = { openDialog.value = false }
                )

                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    )

    if (openDialog2.value) {
        VideosActionDialog(
            openDialog = openDialog,
            openDialog2 = openDialog2,
            fileList = fileList,
            file = fileList[video.value],
            context = context,
        )
    }

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun VideosActionDialog(
    openDialog: MutableState<Boolean>,
    openDialog2: MutableState<Boolean>,
    fileList: SnapshotStateList<File>,
    file: File,
    context: Context
) {

    AlertDialog(
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),
        modifier = Modifier
            .width(250.dp)
            .height(250.dp),
        onDismissRequest = {
            openDialog2.value = false
        },
        buttons = {
            Column(
                modifier = Modifier.padding(all = 8.dp),
                verticalArrangement = Arrangement.Center
            ) {

                Spacer(modifier = Modifier.weight(1f))

                CustomButton(
                    text = stringResource(id = R.string.inspect),
                    buttonSize = 250,
                    onClick = {
                        val intent = Intent()
                        intent.setAction(Intent.ACTION_VIEW)
                        intent.setDataAndType(
                            FileProvider.getUriForFile(
                                context,
                                context.packageName + ".provider",
                                file
                            ), "video/*"
                        )
                        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                        context.startActivity(intent)
                    }
                )

                Spacer(modifier = Modifier.weight(1f))

                CustomButton(
                    text = stringResource(id = R.string.delete),
                    buttonSize = 250,
                    onClick = {
                        openDialog.value = false
                        openDialog2.value = false
                        fileList.remove(file)
                    },
                    buttonColor = Color.Red
                )

                Spacer(modifier = Modifier.weight(1f))

                CustomButton(
                    text = stringResource(id = R.string.go_back),
                    buttonSize = 250,
                    onClick = {
                        openDialog2.value = false
                    }
                )

                Spacer(modifier = Modifier.weight(1f))

            }
        }
    )

}


fun createVideoFile(context: Context, project: String, checklist: String, user: String): File {
    val title = project + "_" + checklist + "_" + user
    val storageDirS = context.filesDir.path + File.separator + "Videos" + File.separator + "Taken"
    val storageDir = File(storageDirS)

    return File.createTempFile(
        "MP4_${title}_",
        ".mp4",
        storageDir
    )
}