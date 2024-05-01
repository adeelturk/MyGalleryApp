package com.turk.mygalleryapp.presentation.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.turk.mygalleryapp.R
import com.turk.mygalleryapp.core.MyGalleryConstants
import com.turk.mygalleryapp.presentation.AppState
import com.turk.mygalleryapp.presentation.GalleryViewModel
import com.turk.mygalleryapp.presentation.ui.screens.GalleryScreen
import com.turk.mygalleryapp.presentation.ui.screens.MediaScreen
import com.turk.mygalleryapp.presentation.ui.theme.SmallTitle


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyGalleryApp(appState: AppState) {

    var screenTitle : String by remember {
        mutableStateOf("")
    }
    val viewModel= hiltViewModel<GalleryViewModel>()
    screenTitle= stringResource(id = R.string.albums)
    viewModel.getAlbums()
    val context= LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Scaffold(
            topBar = {
                TopAppBar(title = { SmallTitle(text = screenTitle) }, colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = Color.Black, titleContentColor = Color.White),navigationIcon = {
                    if (appState.navController.previousBackStackEntry != null) {
                        IconButton(onClick = { appState.navController.navigateUp() }) {
                            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                        }
                    }
                })
            }  ,
            snackbarHost = { SnackbarHost(hostState = appState.snackbarHostState) },
            containerColor = Color.Black,
            contentColor = Color.White,
        ) {
                NavHost(
                    modifier = Modifier.padding(paddingValues = it)
                    ,
                    navController = appState.navController,
                    startDestination = MyGalleryConstants.GalleryScreenNav
                ) {

                    composable(route = MyGalleryConstants.GalleryScreenNav) {
                        GalleryScreen(viewModel){
                            appState.navController.navigate( MyGalleryConstants.ViewMediaGrid)
                        }
                    }

                    composable(
                        route = MyGalleryConstants.ViewMediaGrid
                    ) {

                        MediaScreen(viewModel){
                            var  fileUri = if(it.isVideo){
                                Uri.parse(it.path)
                            }else{
                                it.uri
                            }

                            openMedia(context,fileUri,it.mimeType)
                        }
                    }

            }


        }

    }



}

private fun openMedia(context: Context, fileUri: Uri,mimeType:String) {

    val intent = Intent(Intent.ACTION_VIEW).apply {
        setDataAndType(fileUri, mimeType)
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION) // Add read permission flag if needed
    }
    context.startActivity(intent)
}