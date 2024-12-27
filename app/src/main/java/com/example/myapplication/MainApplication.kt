package com.example.myapplication

import android.app.Application
import androidx.room.Room
import com.example.myapplication.Data.ColorDatabase

class MainApplication:Application() {

companion object{
    lateinit var colorDatabase: ColorDatabase
}

    override fun onCreate() {
        super.onCreate()
       colorDatabase= Room.databaseBuilder(
            applicationContext,
            ColorDatabase::class.java,
            ColorDatabase.NAME
        ).build()   //returns ColorDatabase
    }

}