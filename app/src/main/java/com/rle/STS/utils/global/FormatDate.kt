package com.rle.STS.data.BBDD.Utils

import java.text.SimpleDateFormat
import java.util.*

fun FormatDate(time: Long): String {
    val date = Date(time)
    val format = SimpleDateFormat("EEE, d MMM hh:mm aaa",
        Locale.getDefault())

    return format.format(date)
}