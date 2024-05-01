package com.turk.mygalleryapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.turk.mygalleryapp.presentation.ui.MyGalleryApp
import com.turk.mygalleryapp.presentation.ui.theme.MyGalleryAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyGalleryAppTheme {
                // A surface container using the 'background' color from the theme
                val appState = rememberAppState()
                MyGalleryApp(appState)
            }
        }
    }
}