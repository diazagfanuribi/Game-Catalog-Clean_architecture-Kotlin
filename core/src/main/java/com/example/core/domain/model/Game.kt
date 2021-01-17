package com.example.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Game(
    var pk: Int,
    var name: String,
    var release: String,
    var rating: Float,
    var image_url: String,
    var description: String,
    var image_url_additional: String,
    var website: String,
    var dominant_color: String,
    var genre: String,
    var isFavorite: Boolean = false
) : Parcelable