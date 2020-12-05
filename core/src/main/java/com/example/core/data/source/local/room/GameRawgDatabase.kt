package com.example.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.data.source.local.entity.GameDeveloperEntity
import com.example.core.data.source.local.entity.GameEntity

@Database(
    entities = [GameEntity::class, GameDeveloperEntity::class],
    version = 1,
    exportSchema = false
)
abstract class GameRawgDatabase : RoomDatabase() {
    abstract fun provideGameDao(): GameDao

    abstract fun provideGameDeveloper(): GameDeveloperDao
}