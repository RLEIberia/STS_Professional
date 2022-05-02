package com.rle.STS.Model.Data.BBDD.Utils

import androidx.room.TypeConverter
import java.util.*

class DateConverter {

    @TypeConverter
    fun timeStampFromDate(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun dateFromTimeStamp(timestamp: Long): Date? {
        return Date(timestamp)
    }

}