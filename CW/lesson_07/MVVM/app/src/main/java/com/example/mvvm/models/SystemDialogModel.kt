package com.example.mvvm.models

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class SystemDialogModel(
    val title: String,
    val message: String?,
    val positiveButton: String = "retry",
    val negativeButton: String = "exit"
): Parcelable