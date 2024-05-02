package com.turk.mygalleryapp.presentation.ui.utils

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.annotation.StringRes

fun Context.showPermissionRationale(@StringRes title:Int,
                                    @StringRes message:Int,
                                    @StringRes positiveButtonTitle:Int,
                                    @StringRes cancelButtonTitle:Int) {

    AlertDialog.Builder(this)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(positiveButtonTitle) { _, _ ->
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            val uri = Uri.fromParts("package", packageName, null)
            intent.data = uri
            this.startActivity(intent, null)
        }
        .setNegativeButton(cancelButtonTitle, null)
        .show()
}