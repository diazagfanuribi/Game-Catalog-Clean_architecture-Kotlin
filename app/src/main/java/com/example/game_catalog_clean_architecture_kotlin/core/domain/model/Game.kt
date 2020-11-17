package com.example.game_catalog_clean_architecture_kotlin.core.domain.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Game (
        var pk: Int,
        val name : String,
        val release: String,
        val rating : Float,
        val image_url : String,
        val description : String,
        val image_url_additional: String,
        val website : String,
        val dominant_color : String,
        val genre : String,
        val isFavorite : Boolean = false
)