package com.turk.mygalleryapp.domain.model

import android.net.Uri
import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class Album(val id: Long = 0,
                 val label: String,
                 val uri: Uri,
                 val pathToThumbnail: String,
                 val relativePath: String,
                 var mediaCount:Int=0,
                 val mediaList:ArrayList<Media> = ArrayList()
    ):Parcelable

