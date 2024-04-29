package com.turk.mygalleryapp.presentation.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.turk.mygalleryapp.core.MyGalleryConstants
import com.turk.mygalleryapp.presentation.AppState
import com.turk.mygalleryapp.presentation.ui.screens.GalleryScreen
import com.turk.mygalleryapp.presentation.ui.screens.ViewImageScreen


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyGalleryApp(appState: AppState) {

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Scaffold(
            snackbarHost = { SnackbarHost(hostState = appState.snackbarHostState) },
            containerColor = Color.Transparent,
            contentColor = Color.White
        ) {
            NavHost(
                navController = appState.navController,
                startDestination = MyGalleryConstants.GalleryScreenNav
            ) {

                composable(route = MyGalleryConstants.GalleryScreenNav) {
                    GalleryScreen(){
                        appState.navController.navigate( MyGalleryConstants.ViewImageScreenNav)
                    }
                }

                composable(
                    route = MyGalleryConstants.ViewImageScreenNav
                ) {

                    ViewImageScreen()
                }
            }

        }

    }

}
