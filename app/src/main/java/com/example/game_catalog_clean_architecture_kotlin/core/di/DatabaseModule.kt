package com.example.game_catalog_clean_architecture_kotlin.core.di
import android.content.Context
import androidx.room.Room
import com.example.game_catalog_clean_architecture_kotlin.core.data.source.local.room.GameDao
import com.example.game_catalog_clean_architecture_kotlin.core.data.source.local.room.GameDeveloperDao
import com.example.game_catalog_clean_architecture_kotlin.core.data.source.local.room.GameRawgDatabase

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): GameRawgDatabase = Room.databaseBuilder(
        context,
        GameRawgDatabase::class.java, "Game.db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideGameDao(database: GameRawgDatabase): GameDao = database.provideGameDao()

    @Provides
    fun provideGameDeveloperDao(database: GameRawgDatabase): GameDeveloperDao = database.provideGameDeveloper()
}