package com.example.game_catalog_clean_architecture_kotlin.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GameDeveloperResponse(
        @field:SerializedName("id")
        val id : Int,

        @field:SerializedName("name")
        val name : String,

        @field:SerializedName("image_background")
        val image_url : String
): Parcelable