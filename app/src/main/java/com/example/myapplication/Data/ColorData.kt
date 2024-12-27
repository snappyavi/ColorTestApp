package com.example.myapplication.Data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class ColorData(
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    val hexCode:String,
    val createdAt: Date
)




//
//val Colors= listOf(
//    ColorData("Light Blue","#AED6F1"),
//    ColorData("Pastel Red", "#F1948A")
//)
