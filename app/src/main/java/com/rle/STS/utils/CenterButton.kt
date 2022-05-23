package com.rle.STS.utils

import com.rle.STS.R

enum class CenterButton (

    val centerActive: Boolean = false,
    val centerText: String = "CENTER",
    val centerIcon: Int = R.drawable.options,
    val centerOnClick: () -> Unit = {}

)