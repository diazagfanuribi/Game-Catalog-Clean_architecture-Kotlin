package com.example.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class GameList(
    val id: Int,

    val name: String,

    val release: String,

    val rating: Float,

    val image_url: String,

    val isFavorite: Boolean = false
) : Parcelable
