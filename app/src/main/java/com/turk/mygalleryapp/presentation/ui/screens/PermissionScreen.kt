package com.turk.mygalleryapp.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.turk.mygalleryapp.R
import com.turk.mygalleryapp.presentation.ui.theme.SmallBody
import com.turk.mygalleryapp.presentation.ui.theme.SmallHeading
import com.turk.mygalleryapp.presentation.ui.theme.SmallTitle
import com.turk.mygalleryapp.presentation.ui.utils.MyGalleryConstants
import com.turk.mygalleryapp.presentation.ui.utils.showPermissionRationale


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionScreen(onPermissionGranted:()->Unit) {
    val context = LocalContext.current
    var isPermissionGranted: String by remember { mutableStateOf(MyGalleryConstants.PERMISSION_NOT_REQUESTED) }
    val mediaPermissions = rememberMultiplePermissionsState(MyGalleryConstants.PERMISSIONS) {
         it.all { item -> item.value }.run {
             isPermissionGranted = if(this){
                 MyGalleryConstants.PERMISSION_GRANTED
             }else{
                 MyGalleryConstants.PERMISSION_NOT_GRANTED
             }
         }
        if (isPermissionGranted==MyGalleryConstants.PERMISSION_GRANTED) {
            onPermissionGranted()
        } else if (isPermissionGranted==MyGalleryConstants.PERMISSION_NOT_GRANTED) {
            context.showPermissionRationale(R.string.requiredPermission,R.string.permission_dialog_message,R.string.grant,R.string.cancel)
        }
    }

    Box(modifier= Modifier
        .background(Color.Transparent)
        .fillMaxSize()) {


        Column(Modifier.align(Alignment.Center)
           , // This ensures that the Column occupies the entire available space
            horizontalAlignment = Alignment.CenterHorizontally) {

            SmallHeading(text = stringResource(id = R.string.requiredPermission))
            SmallBody(text = stringResource(id = R.string.permission_msg), modifier = Modifier.padding(20.dp) )

            Button(onClick = { mediaPermissions.launchMultiplePermissionRequest() }) {
                SmallTitle(text = "Grant")
            }
        }



    }



}