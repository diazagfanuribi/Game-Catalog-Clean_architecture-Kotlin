package com.example.game_catalog_clean_architecture_kotlin.core.di
import android.content.Context
import androidx.room.Room
import com.example.game_catalog_clean_architecture_kotlin.core.data.source.local.room.GameDao
import com.example.game_catalog_clean_architecture_kotlin.core.data.source.local.room.GameRawgDatabase

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): GameRawgDatabase = Room.databaseBuilder(
        context,
        GameRawgDatabase::class.java, "Tourism.db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideTourismDao(database: GameRawgDatabase): GameDao = database.provideGameDao()
}