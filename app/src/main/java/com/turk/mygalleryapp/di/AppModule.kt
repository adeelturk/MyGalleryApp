package com.turk.mygalleryapp.di

import android.content.ContentResolver
import android.content.Context
import com.turk.mygalleryapp.data.repository.GalleryRepositoryImpl
import com.turk.mygalleryapp.domain.repo.GalleryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideContentResolver(@ApplicationContext context: Context): ContentResolver {
        return context.contentResolver
    }


    @Provides
    @Singleton
    fun providesGalleryRepository(contentResolver: ContentResolver):GalleryRepository{

        return GalleryRepositoryImpl(contentResolver)

    }


}
