package com.example.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GameDeveloperResponse(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("image_background")
    val image_url: String
)