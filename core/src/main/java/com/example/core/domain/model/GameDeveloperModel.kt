package com.example.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GameDeveloperModel(
    val id: Int,

    val name: String,

    val image_url: String
):Parcelable