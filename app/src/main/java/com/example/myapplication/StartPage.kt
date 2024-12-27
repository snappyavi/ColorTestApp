package com.example.myapplication

import android.service.autofill.OnClickAction
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.Data.ColorData
import com.example.myapplication.ui.theme.Comfortaa

import com.example.myapplication.viewModel.ColorViewModel
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.random.Random


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartPage(
    modifier: Modifier = Modifier, viewModel: ColorViewModel
) {

    val colorList by viewModel.colorList.observeAsState(emptyList())  //colorList as state

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    fun getRandomColorHex(): Color {
        val red = Random.nextInt(256)
        val green = Random.nextInt(256)
        val blue = Random.nextInt(256)
        val alpha = Random.nextInt(256)

        //   val color= Color(red, green, blue, alpha)
        return Color(red, green, blue, alpha)
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Color App",
                        fontFamily = Comfortaa, fontWeight = FontWeight.SemiBold,)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                actions = {
                    ExtendedFloatingActionButton(
                        modifier = Modifier.height(40.dp), shape = RoundedCornerShape(30.dp),
                        containerColor = MaterialTheme.colorScheme.primary,
                        onClick = {},
                        text = { Text(text = "12",fontFamily = Comfortaa,) },
                        icon = {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.upload),
                                "upload"
                            )
                        },
                    )
                },
                scrollBehavior = scrollBehavior,
            )
        },

        floatingActionButton = {
            ExtendedFloatingActionButton(
                shape = RoundedCornerShape(30.dp),
                onClick = {

                    //random color is generated at the moment of click, previously it was generated before the click
                    val randomColor = getRandomColorHex()

                    val randomColorHex = "#${Integer.toHexString(randomColor.toArgb())}"

                    Log.d("StartPage", "Adding color: $randomColorHex")
                    viewModel.addColor(randomColorHex)
                },
                // onClick = { viewModel.addColor(randomColorHex) },  //converts color to hex string
                text = { Text(text = "Add Colors") },
                icon = { Icon(Icons.Filled.AddCircle, "Add Color") },
            )
        }


    ) { innerpadding ->
        // Content inside the Scaffold goes here


        colorList?.let {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = modifier
                    .padding(innerpadding),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp), // Added spacing
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(colorList) { colorData ->
                    ColorCardView(
                        colorData = colorData
                    )
                }
            }
        }

    }


}


@Composable
fun ColorCardView(modifier: Modifier = Modifier, colorData: ColorData) {
    val backgroundColor = try {
        Color(android.graphics.Color.parseColor(colorData.hexCode))
    } catch (e: IllegalArgumentException) {
        // Handle invalid hex code (use a default color)
        Color.Gray // Default color
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
            .clip(
                RoundedCornerShape(20.dp)
            ),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
    ) {
        Column(modifier = Modifier.padding(8.dp)) {

            Text(

                text = "${colorData.hexCode}",
                fontFamily = Comfortaa,
                fontSize = 16.sp,
                textAlign = TextAlign.Left, fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline,
                color = Color.White
            )
            Spacer(modifier = modifier.height(60.dp))

            Text(
                   modifier = modifier.width(150.dp),
                text = SimpleDateFormat("HH:mm:aa", Locale.ENGLISH).format(colorData.createdAt),
                textAlign = TextAlign.Right,
                fontFamily = Comfortaa, fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp,
                color = Color.White
            )
        }
    }
}





