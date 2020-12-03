package com.example.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "game")
data class GameEntity(
        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "pk")
        var pk: Int,

        @ColumnInfo(name ="name")
        var name : String,

        @ColumnInfo(name ="released_date")
        var release: String,

        @ColumnInfo(name ="rating")
        var rating : Float,

        @ColumnInfo(name ="background_image")
        var image_url : String,

        @ColumnInfo(name ="description")
        var description : String,

        @ColumnInfo(name ="background_image_additional")
        var image_url_additional: String,

        @ColumnInfo(name ="website")
        var website : String,

        @ColumnInfo(name ="dominant_color")
        var dominant_color : String,

        @ColumnInfo(name ="genres")
        var genre : String,

        @ColumnInfo(name ="isFavorite")
        var isFavorite : Boolean = false
)