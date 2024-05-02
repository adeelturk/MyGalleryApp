package com.turk.mygalleryapp.data.datasource

import android.content.ContentResolver
import android.content.ContentUris
import android.database.Cursor
import android.database.MergeCursor
import android.provider.MediaStore
import com.turk.mygalleryapp.domain.extension.getGivenDataFromAlbums
import com.turk.mygalleryapp.domain.model.Album
import com.turk.mygalleryapp.domain.model.GalleryData
import com.turk.mygalleryapp.domain.model.Media
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun ContentResolver.getGalleryData(): GalleryData {

    return withContext(Dispatchers.IO) {
        val galleryData = GalleryData()
        val foldersMap = HashMap<String, Album>()
        query().use {

            while (it.moveToNext()) {
                try {
                    val albumId =
                        it.getLong(it.getColumnIndexOrThrow(MediaStore.MediaColumns.BUCKET_ID))
                    val mediaId = it.getLong(it.getColumnIndexOrThrow(MediaStore.MediaColumns._ID))
                    val albumName =
                        it.getString(it.getColumnIndexOrThrow(MediaStore.MediaColumns.BUCKET_DISPLAY_NAME))
                            ?: ""
                    val mediaName =
                        it.getString(it.getColumnIndexOrThrow(MediaStore.MediaColumns.DISPLAY_NAME))
                            ?: ""
                    val mimeType =
                        it.getString(it.getColumnIndexOrThrow(MediaStore.MediaColumns.MIME_TYPE))
                    val contentUri = if (mimeType.contains("image"))
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                    else
                        MediaStore.Video.Media.EXTERNAL_CONTENT_URI

                    val path =
                        it.getString(it.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA))

                    val relativePath =
                        it.getString(it.getColumnIndexOrThrow(MediaStore.MediaColumns.RELATIVE_PATH))


                    val folder = Album(albumId, albumName,  ContentUris.withAppendedId(contentUri, mediaId), path, relativePath)
                    if (foldersMap.containsKey(albumName)) {
                        foldersMap[albumName]?.run {
                            this.mediaCount += 1
                            this.mediaList.add( Media(
                                mediaId,
                                mediaName,
                                ContentUris.withAppendedId(contentUri, mediaId),
                                path,
                                relativePath,
                                albumId,
                                albumName,
                                mimeType
                            ))
                        }
                    } else {
                        folder.mediaCount += 1
                        folder.mediaList.add(
                            Media(
                                mediaId,
                                mediaName,
                                 ContentUris.withAppendedId(contentUri, mediaId),
                                path,
                                relativePath,
                                albumId,
                                albumName,
                                mimeType
                            )
                        )
                        foldersMap[albumName] = folder
                    }

                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
            }
            galleryData.albumsList .addAll( foldersMap.filter {
                it.value.label.isNotBlank()
            }.map {
                it.value
            }.sortedBy {
                it.label
            })

            galleryData.allImagesList = galleryData.albumsList.getGivenDataFromAlbums(true)
            galleryData.allVideosList = galleryData.albumsList.getGivenDataFromAlbums(false)
            galleryData.allImagesList.first().run {
                galleryData.albumsList.add(0,Album(label="All Images",
                    uri=this.uri,
                    pathToThumbnail = this.path,
                    relativePath = this.relativePath,
                    mediaCount = galleryData.allImagesList.size,
                    mediaList = galleryData.allImagesList
                    ))
            }

            galleryData.allVideosList.first().run {
                galleryData.albumsList.add(0,Album(label="All Video",
                    uri=this.uri,
                    pathToThumbnail = this.path,
                    relativePath = this.relativePath,
                    mediaCount = galleryData.allVideosList.size,
                    mediaList = galleryData.allVideosList
                ))
            }


            it.close()
        }

        return@withContext galleryData
    }


}

suspend fun ContentResolver.query(
): Cursor {
    val sortOrder = "${MediaStore.MediaColumns.DATE_ADDED} DESC"
    return withContext(Dispatchers.IO) {
        return@withContext MergeCursor(
            arrayOf(
                query(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    MediaQuery.projection,
                    null,
                    null,
                    sortOrder
                ),
                query(
                    MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                    MediaQuery.projection,
                    null,
                    null,
                    sortOrder
                )
            )
        )
    }
}
