package com.turk.mygalleryapp.domain.model

data class GalleryData(var albumsList:ArrayList<Album> =ArrayList(),
                       var allImagesList:ArrayList<Media> =ArrayList(),
                       var allVideosList:ArrayList<Media> =ArrayList())
