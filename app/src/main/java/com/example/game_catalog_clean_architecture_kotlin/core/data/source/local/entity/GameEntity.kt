package com.example.game_catalog_clean_architecture_kotlin.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.game_catalog_clean_architecture_kotlin.core.data.source.remote.response.GameDetailResponse
import com.google.gson.annotations.SerializedName

@Entity(tableName = "game")
data class GameEntity(
        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "pk")
        var pk: Int,

        @ColumnInfo(name ="name")
        val name : String,

        @ColumnInfo(name ="released_date")
        val release: String,

        @ColumnInfo(name ="rating")
        val rating : Float,

        @ColumnInfo(name ="background_image")
        val image_url : String,

        @ColumnInfo(name ="description")
        val description : String,

        @ColumnInfo(name ="background_image_additional")
        val image_url_additional: String,

        @ColumnInfo(name ="website")
        val website : String,

        @ColumnInfo(name ="dominant_color")
        val dominant_color : String,

        @ColumnInfo(name ="genres")
        val genre : String,

        @ColumnInfo(name ="isFavorite")
        val isFavorite : Boolean = false
)