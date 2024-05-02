package com.turk.mygalleryapp.presentation.ui.utils

import android.Manifest
import android.os.Build
import androidx.annotation.RequiresApi

object MyGalleryConstants {

    const val PermissionScreenNav="permissionScreen"
    const val GalleryScreenNav="galleryScreen"
    const val ViewMediaGrid="mediaGrid"
    const val PERMISSION_NOT_REQUESTED="notRequested"
    const val PERMISSION_NOT_GRANTED="notGranted"
    const val PERMISSION_GRANTED="granted"


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private val PERMISSION_T =  listOf(
        Manifest.permission.READ_MEDIA_IMAGES,
        Manifest.permission.READ_MEDIA_VIDEO
    )
    private val PERMISSION_OLD =
        listOf(Manifest.permission.READ_EXTERNAL_STORAGE)

    val PERMISSIONS = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        PERMISSION_T
    } else {
        PERMISSION_OLD
    }

}