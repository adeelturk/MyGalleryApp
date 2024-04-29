package com.turk.mygalleryapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.turk.mygalleryapp.presentation.ui.MyGalleryApp
import com.turk.mygalleryapp.presentation.ui.theme.MyGalleryAppTheme

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