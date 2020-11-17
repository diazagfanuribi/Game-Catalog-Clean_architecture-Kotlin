package com.example.game_catalog_clean_architecture_kotlin.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListGameDeveloperResponses(
        @field:SerializedName("count")
        val count : Int,

        @field:SerializedName("next")
        val next : String,

        @field:SerializedName("previous")
        val previous : String?,

        @field:SerializedName("results")
        val results : List<GameDeveloperResponse>
)