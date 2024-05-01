/*
 * SPDX-FileCopyrightText: 2023 IacobIacob01
 * SPDX-License-Identifier: Apache-2.0
 */

package com.turk.mygalleryapp.data.datasource

import android.provider.MediaStore

object MediaQuery {
   val  projection =arrayOf(
    MediaStore.MediaColumns._ID,
    MediaStore.MediaColumns.BUCKET_ID,
    MediaStore.MediaColumns.BUCKET_DISPLAY_NAME,
    MediaStore.MediaColumns.DISPLAY_NAME,
    MediaStore.MediaColumns.DATA,
    MediaStore.MediaColumns.RELATIVE_PATH,
    MediaStore.MediaColumns.MIME_TYPE,
    MediaStore.Video.Thumbnails.DATA

   )

}