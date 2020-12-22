package com.example.core.data.source.local

import com.example.core.data.source.local.entity.GameDeveloperEntity
import com.example.core.data.source.local.entity.GameEntity
import com.example.core.data.source.local.entity.GameListEntity
import com.example.core.data.source.local.room.GameDao
import com.example.core.data.source.local.room.GameDeveloperDao
import com.example.core.data.source.local.room.GamelistDao
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    val gameDao: GameDao,
    val gameDeveloperDao: GameDeveloperDao,
    val gameListDao: GamelistDao
) {
    fun getGameList(): Flowable<List<GameListEntity>> = gameListDao.getGameList()

    fun addGameList(gameList : List<GameListEntity>) = gameListDao.addGameList(gameList)

    fun getAllDeveloper(): Flowable<List<GameDeveloperEntity>> = gameDeveloperDao.getAllDeveloper()

    fun addDeveloper(developerEntities: List<GameDeveloperEntity>) =
        gameDeveloperDao.addDeveloper(developerEntities)

    fun getFavorite(): Flowable<List<GameEntity>> = gameDao.getFavorites()

    fun countFavorite() = gameDao.countFavorite()

    fun updateFavorite(gameEntity: GameEntity) = gameDao.updateFavorite(gameEntity)

    fun addGame(gameEntity: GameEntity) = gameDao.addGame(gameEntity)

    fun getGameById(id : Int): Flowable<List<GameEntity>> {
        return gameDao.getGameById(id = id)
    }
}