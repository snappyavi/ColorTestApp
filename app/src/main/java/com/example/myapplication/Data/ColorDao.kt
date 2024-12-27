package com.example.myapplication.Data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ColorDao {

    @Query("SELECT * FROM colordata ORDER BY createdAt DESC")  //descending order; latest first
     fun getAllColor(): LiveData<List<ColorData>>

    @Insert
  fun addColor(colorData: ColorData)

    @Query("DELETE FROM colordata where id=:id")
  fun deleteColor(id: Int)

}