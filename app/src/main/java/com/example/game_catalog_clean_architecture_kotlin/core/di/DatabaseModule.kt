package com.dicoding.tourismapp.core.di

import android.content.Context
import androidx.room.Room

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

//    @Singleton
//    @Provides
//    fun provideDatabase(context: Context): TourismDatabase = Room.databaseBuilder(
//        context,
//        TourismDatabase::class.java, "Tourism.db"
//    ).fallbackToDestructiveMigration().build()
//
//    @Provides
//    fun provideTourismDao(database: TourismDatabase): TourismDao = database.tourismDao()
}