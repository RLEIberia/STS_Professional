package com.rle.STS.logic.checklist

import android.util.Log
import com.rle.STS.model.JSON.checklistStructure.Path

fun nextStep() {

    val step: Int //TODO From checklistViewModel
    val choice: Int = 0//TODO From checklistViewModel
    val path: ArrayList<Path> = ArrayList()

    when(path[choice].actionCode) {

        //End checklist
        "000" -> /*TODO - End checklist*/
            Log.d("pathAction", "000")
        "001" -> /*TODO - End checklist with message*/
            Log.d("pathAction", "000")

        //Continue
        "100" -> {
            step = path[choice].nextStep[0]
        }




    }

}