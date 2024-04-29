package com.turk.mygalleryapp.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.turk.mygalleryapp.presentation.ui.theme.smallUnit


@Composable
fun ViewImageScreen(){

    Box ( modifier = Modifier
        .fillMaxSize()
        .padding(smallUnit).background(color= Color.Black),
        contentAlignment = Alignment.Center){



    }

}

@Preview
@Composable
fun ShowViewImageScreen(){

    ViewImageScreen()
}