package com.example.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core.data.source.local.entity.GameListEntity
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface GamelistDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addGameList(games: List<GameListEntity>): Completable

    @Query("SElECT * FROM gameList")
    fun getGameList(): Flowable<List<GameListEntity>>
}