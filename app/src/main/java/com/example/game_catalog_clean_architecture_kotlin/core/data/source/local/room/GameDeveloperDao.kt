package com.example.game_catalog_clean_architecture_kotlin.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.game_catalog_clean_architecture_kotlin.core.data.source.local.entity.GameDeveloperEntity
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface GameDeveloperDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addDeveloper(developer : List<GameDeveloperEntity>): Completable

    @Query("SELECT * FROM gameDeveloper")
    fun getAllDeveloper(): Flowable<List<GameDeveloperEntity>>
}