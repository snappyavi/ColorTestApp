package com.example.myapplication

import androidx.room.TypeConverter
import java.util.Date

class Converters {
    @TypeConverter  // define custom conversion logic between non-supported types
    fun fromDate(date: Date): Long {
        return date.time  //rx date and return time to milliseconds
    }  //Room uses this conversion to store a Date in a database column that can only hold Long values.

    @TypeConverter
    fun toDate(time: Long): Date {
        return Date(time)   //vice-versa when querying
    }

}