package com.example.core.data.source.local

import com.example.core.data.source.local.entity.GameDeveloperEntity
import com.example.core.data.source.local.entity.GameEntity
import com.example.core.data.source.local.room.GameDao
import com.example.core.data.source.local.room.GameDeveloperDao
import io.reactivex.Flowable
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    val gameDao: GameDao,
    val gameDeveloperDao: GameDeveloperDao
) {
    fun getAllDeveloper(): Flowable<List<GameDeveloperEntity>> = gameDeveloperDao.getAllDeveloper()

    fun addDeveloper(developerEntities: List<GameDeveloperEntity>) =
        gameDeveloperDao.addDeveloper(developerEntities)

    fun getFavorite(): Flowable<List<GameEntity>> = gameDao.getFavorites()

    fun updateFavorite(gameEntity: GameEntity) = gameDao.updateFavorite(gameEntity)

}