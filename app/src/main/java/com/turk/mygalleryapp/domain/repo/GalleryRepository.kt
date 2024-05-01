package com.turk.mygalleryapp.domain.repo

import com.turk.mygalleryapp.domain.model.GalleryData
import kotlinx.coroutines.flow.Flow

interface GalleryRepository {

    suspend fun fetchGalleryData() : Flow<GalleryData>
}