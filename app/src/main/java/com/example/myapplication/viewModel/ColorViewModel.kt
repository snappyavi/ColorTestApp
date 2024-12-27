package com.example.myapplication.viewModel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Data.ColorData
import com.example.myapplication.MainApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Date

class ColorViewModel : ViewModel() {
    val colorDao = MainApplication.colorDatabase.getColorDao()

    val colorList: LiveData<List<ColorData>> = colorDao.getAllColor()

    @SuppressLint("NewApi")
    fun addColor(hexCode: String) {

        //Task Transfered to different thread for loading
        viewModelScope.launch(Dispatchers.IO) {
            colorDao.addColor(ColorData(hexCode = hexCode, createdAt = Date.from(Instant.now())))
        }

    }

    fun deleteColor(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            colorDao.deleteColor(id)
        }

    }

}