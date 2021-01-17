package com.example.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gameDeveloper")
data class GameDeveloperEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "pk")
    var id: Int,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "image_url")
    var image_url: String
)