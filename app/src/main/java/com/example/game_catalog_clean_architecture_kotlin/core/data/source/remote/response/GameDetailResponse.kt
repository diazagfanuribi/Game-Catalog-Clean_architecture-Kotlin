package com.example.game_catalog_clean_architecture_kotlin.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GameDetailResponse(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("released")
    val release: String,

    @field:SerializedName("rating")
    val rating: Float,

    @field:SerializedName("background_image")
    val image_url: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("background_image_additional")
    val image_url_additional: String,

    @field:SerializedName("website")
    val website: String,

    @field:SerializedName("dominant_color")
    val dominant_color: String,

    @field:SerializedName("genres")
    val genre: List<Genre>,

    val isFavorite: Boolean = false
) : Parcelable {
    @Parcelize
    data class Genre(
        @field:SerializedName("name")
        val name: String,

        @field:SerializedName("image_background")
        val image: String,
    ) : Parcelable

    val genres: String
        get() {
            var genreString = ""
            genre.map {
                genreString += it.name + ", "
            }
            return genreString
        }

}