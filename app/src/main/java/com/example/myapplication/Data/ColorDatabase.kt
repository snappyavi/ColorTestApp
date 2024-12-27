package com.example.myapplication.Data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapplication.Converters

@Database(entities = [ColorData::class], version = 1)
@TypeConverters(Converters::class)
abstract class ColorDatabase:RoomDatabase() {

    companion object{
        const val NAME="Color_DB"
    }

    abstract fun getColorDao():ColorDao

}