package com.turk.mygalleryapp.domain.model

import android.net.Uri
import android.os.Parcelable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class Media(val id: Long = 0,
                 val label: String,
                 val uri: Uri,
                 val path: String,
                 val relativePath: String,
                 val albumID: Long,
                 val albumLabel: String,
                 val mimeType: String,): Parcelable
{


    @IgnoredOnParcel
    @Stable
    val isVideo: Boolean = mimeType.startsWith("video/")

    @IgnoredOnParcel
    @Stable
    val isImage: Boolean = mimeType.startsWith("image/")

 }
