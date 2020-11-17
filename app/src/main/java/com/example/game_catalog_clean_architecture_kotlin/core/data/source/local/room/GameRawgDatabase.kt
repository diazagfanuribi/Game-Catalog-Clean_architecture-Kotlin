package com.example.game_catalog_clean_architecture_kotlin.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.game_catalog_clean_architecture_kotlin.core.data.source.local.entity.GameEntity

@Database(entities = [GameEntity::class], version = 1, exportSchema = false)
abstract class GameRawgDatabase : RoomDatabase(){
    abstract  fun provideGameDao(): GameDao
}