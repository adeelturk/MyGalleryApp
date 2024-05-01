package com.turk.mygalleryapp.domain.usecase

import com.turk.mygalleryapp.domain.model.GalleryData
import com.turk.mygalleryapp.domain.repo.GalleryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchGalleryUseCase @Inject constructor(private val galleryRepo:GalleryRepository) {

   suspend operator fun invoke(): Flow<GalleryData> =galleryRepo.fetchGalleryData()
}