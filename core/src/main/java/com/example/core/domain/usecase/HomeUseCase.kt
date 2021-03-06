package com.example.core.domain.usecase

import com.example.core.data.Resource
import com.example.core.domain.model.Game
import com.example.core.domain.model.GameDeveloperModel
import com.example.core.domain.model.GameList
import io.reactivex.Completable
import io.reactivex.Flowable

interface HomeUseCase {
    fun getDeveloper(): Flowable<Resource<List<GameDeveloperModel>>>

    fun getFavorite(): Flowable<List<Game>>

    fun setFavorite(game: Game, state: Boolean): Completable

    fun getGameById(id: Int): Flowable<Resource<List<Game>>>

    fun getGameList(): Flowable<Resource<List<GameList>>>

}