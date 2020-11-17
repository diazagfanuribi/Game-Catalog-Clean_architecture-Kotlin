package com.example.game_catalog_clean_architecture_kotlin.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ListGameResponses(
        @field:SerializedName("count")
        val count : Int,

        @field:SerializedName("next")
        val next : String,

        @field:SerializedName("previous")
        val previous : String?,

        @field:SerializedName("results")
        val results : List<GameResponse>

): Parcelable