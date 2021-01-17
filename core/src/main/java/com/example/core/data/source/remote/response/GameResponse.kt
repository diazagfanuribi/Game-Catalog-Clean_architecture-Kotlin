package com.example.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GameResponse(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("name")
    val name: String?,

    @field:SerializedName("released")
    val release: String?,

    @field:SerializedName("rating")
    val rating: Float?,

    @field:SerializedName("background_image")
    val image_url: String?,

    val isFavorite: Boolean = false
)