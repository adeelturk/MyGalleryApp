package com.turk.mygalleryapp.domain.extension

import com.turk.mygalleryapp.domain.model.Album
import com.turk.mygalleryapp.domain.model.Media
 fun List<Album>.getGivenDataFromAlbums(shouldGetImage:Boolean=false): ArrayList<Media> {
    val allMedia = ArrayList<Media>()

    for (album in this) {
        allMedia.addAll(album.mediaList.filter {
            if(shouldGetImage)
                it.isImage
            else
                it.isVideo
        })
    }

    return allMedia
}