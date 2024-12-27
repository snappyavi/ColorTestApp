package com.example.myapplication

import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.myapplication.Data.ColorData
import com.example.myapplication.viewModel.ColorViewModel

@Composable
fun MainPage(modifier: Modifier = Modifier) {
    StartPage(viewModel = ColorViewModel())
}