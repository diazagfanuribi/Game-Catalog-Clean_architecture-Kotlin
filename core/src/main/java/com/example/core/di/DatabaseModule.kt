package com.example.core.di

import android.content.Context
import androidx.room.Room
import com.example.core.data.source.local.room.GameDao
import com.example.core.data.source.local.room.GameDeveloperDao
import com.example.core.data.source.local.room.GameRawgDatabase
import com.example.core.data.source.local.room.GamelistDao

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): GameRawgDatabase {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("dicoding".toCharArray())
        val factory = SupportFactory(passphrase)
        return Room.databaseBuilder(
            context,
            GameRawgDatabase::class.java, "Game.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }

    @Provides
    fun provideGameDao(database: GameRawgDatabase): GameDao = database.provideGameDao()

    @Provides
    fun provideGameDeveloperDao(database: GameRawgDatabase): GameDeveloperDao =
        database.provideGameDeveloper()

    @Provides
    fun provideGameListDao(database: GameRawgDatabase): GamelistDao = database.provideGameList()

}