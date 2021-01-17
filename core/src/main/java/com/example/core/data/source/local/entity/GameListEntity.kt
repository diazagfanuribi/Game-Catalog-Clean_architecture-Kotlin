package com.example.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gameList")
data class GameListEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "release")
    val release: String,
    @ColumnInfo(name = "rating")
    val rating: Float,
    @ColumnInfo(name = "image_url")
    val image_url: String,
    @ColumnInfo(name = "isFavorite")
    val isFavorite: Boolean
)