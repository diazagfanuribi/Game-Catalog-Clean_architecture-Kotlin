package com.example.core.data.source.local.room

import androidx.room.*
import com.example.core.data.source.local.entity.GameEntity
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface GameDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addGames(games : List<GameEntity>) : Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addGame(games : GameEntity) : Completable

    @Update
    fun updateFavorite(game: GameEntity): Completable

    @Query("SElECT * FROM game WHERE isFavorite = 1")
    fun getFavorites(): Flowable<List<GameEntity>>

    @Query("SElECT * FROM game")
    fun getGame(): Flowable<GameEntity>

    @Query("SElECT * FROM game WHERE pk = :id")
    fun getGameById(id: Int): Flowable<GameEntity>
}