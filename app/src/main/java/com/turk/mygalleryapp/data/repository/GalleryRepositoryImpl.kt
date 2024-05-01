package com.turk.mygalleryapp.data.repository

import android.content.ContentResolver
import com.turk.mygalleryapp.data.datasource.getGalleryData
import com.turk.mygalleryapp.domain.model.GalleryData
import com.turk.mygalleryapp.domain.repo.GalleryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GalleryRepositoryImpl @Inject constructor(private val contentResolver:ContentResolver) : GalleryRepository {

    override suspend fun fetchGalleryData(): Flow<GalleryData> {

       return flow { emit(contentResolver.getGalleryData()) }
    }
}